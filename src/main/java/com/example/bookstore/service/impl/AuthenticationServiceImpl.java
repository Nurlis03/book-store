package com.example.bookstore.service.impl;

import com.example.bookstore.config.JwtService;
import com.example.bookstore.dto.AuthenticationResponse;
import com.example.bookstore.dto.SignInRequestDto;
import com.example.bookstore.dto.SignUpRequestDto;
import com.example.bookstore.entity.User;
import com.example.bookstore.exception.UserAlreadyExistsException;
import com.example.bookstore.mapper.AuthenticationMapper;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.AuthenticationService;
import com.example.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationMapper authenticationMapper;

    @Override
    public AuthenticationResponse signUp(SignUpRequestDto signUpRequestDto) {
        log.info(signUpRequestDto.toString());
        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new UserAlreadyExistsException(signUpRequestDto.getEmail());
        }
        User user = authenticationMapper.fromSignUpRequestDtoToUser(signUpRequestDto);
        userRepository.save(user);
        String accessToken = jwtService.generateToken(user.getEmail());
        return AuthenticationResponse
                .builder()
                .accessToken(accessToken)
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequestDto signInRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDto.getEmail(),
                        signInRequestDto.getPassword()
                )
        );
        User user = userRepository.findByEmail(signInRequestDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + signInRequestDto.getEmail()));
        String accessToken = jwtService.generateToken(user.getEmail());
        return AuthenticationResponse
                .builder()
                .accessToken(accessToken)
                .build();
    }
}
