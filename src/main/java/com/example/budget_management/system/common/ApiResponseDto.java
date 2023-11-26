package com.example.budget_management.system.common;

import lombok.Getter;

@Getter
public class ApiResponseDto {
    private int statusCode;
    private String statusMessage;

    public ApiResponseDto(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}