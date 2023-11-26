package com.example.budget_management.system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CustomErrorCode {
    NOT_FOLLOW_RULES(HttpStatus.BAD_REQUEST.value(),
            "숫자, 문자, 특수문자 중 2가지 이상을 포함해야 합니다."),
    NOT_THREE_CONSECUTIVE(HttpStatus.BAD_REQUEST.value(),
			"3회 이상 연속되는 문자 사용이 불가합니다.");

    private final int errorCode;
    private final String errorMessage;

}
