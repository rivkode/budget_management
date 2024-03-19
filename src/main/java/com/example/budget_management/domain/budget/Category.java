package com.example.budget_management.domain.budget;

import com.example.budget_management.system.exception.CustomErrorCode;
import com.example.budget_management.system.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Category {
    TRANSPORT("교통비"),
    FOOD("식비"),
    ENTERTAINMENT("문화"),
    ETC("기타");

    @Getter
    private final String value;

}
