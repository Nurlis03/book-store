package com.example.bookstore.controller;

import com.example.bookstore.dto.AuthenticationResponse;
import com.example.bookstore.dto.SignInRequestDto;
import com.example.bookstore.dto.SignUpRequestDto;
import com.example.bookstore.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public AuthenticationResponse signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return authenticationService.signUp(signUpRequestDto);
    }

    @PostMapping("/sign-in")
    public AuthenticationResponse signIn(@RequestBody SignInRequestDto signInRequestDto) {
        return authenticationService.signIn(signInRequestDto);
    }
}
