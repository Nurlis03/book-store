package com.example.bookstore.dto;

import com.example.bookstore.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignUpRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
