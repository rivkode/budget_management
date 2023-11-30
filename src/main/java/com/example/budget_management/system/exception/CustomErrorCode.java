package com.example.budget_management.system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CustomErrorCode {
    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(),
            "이미 존재하는 사용자입니다."),
    NOT_FOLLOW_RULES(HttpStatus.BAD_REQUEST.value(),
            "숫자, 문자, 특수문자 중 2가지 이상을 포함해야 합니다."),
    NOT_THREE_CONSECUTIVE(HttpStatus.BAD_REQUEST.value(),
			"3회 이상 연속되는 문자 사용이 불가합니다."),
    NOT_EXIST_BUDGET(HttpStatus.BAD_REQUEST.value(),
            "예산이 존재하지 않습니다");

    private final int errorCode;
    private final String errorMessage;

}
