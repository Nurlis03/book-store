package com.example.bookstore.service;

import com.example.bookstore.dto.AuthenticationResponse;
import com.example.bookstore.dto.SignInRequestDto;
import com.example.bookstore.dto.SignUpRequestDto;

import javax.persistence.Inheritance;

public interface AuthenticationService {
    AuthenticationResponse signUp(SignUpRequestDto signUpRequestDto);
    AuthenticationResponse signIn(SignInRequestDto signInRequestDto);
}