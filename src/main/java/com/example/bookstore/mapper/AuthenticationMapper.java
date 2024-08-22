package com.example.bookstore.mapper;

import com.example.bookstore.dto.SignUpRequestDto;
import com.example.bookstore.entity.User;

public interface AuthenticationMapper {

    User fromSignUpRequestDtoToUser(SignUpRequestDto signUpRequestDto);

}
