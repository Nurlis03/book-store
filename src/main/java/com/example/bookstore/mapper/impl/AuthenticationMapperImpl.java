package com.example.bookstore.mapper.impl;

import com.example.bookstore.dto.SignUpRequestDto;
import com.example.bookstore.entity.User;
import com.example.bookstore.mapper.AuthenticationMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationMapperImpl implements AuthenticationMapper {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User fromSignUpRequestDtoToUser(SignUpRequestDto signUpRequestDto) {
        return User
                .builder()
                .firstName(signUpRequestDto.getFirstName())
                .lastName(signUpRequestDto.getLastName())
                .email(signUpRequestDto.getEmail())
                .role(signUpRequestDto.getRole())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .build();
    }

}
