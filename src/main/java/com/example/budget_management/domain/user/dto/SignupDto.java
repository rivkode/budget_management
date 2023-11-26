package com.example.budget_management.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignupDto {
    private String email;
    private String password;
}
