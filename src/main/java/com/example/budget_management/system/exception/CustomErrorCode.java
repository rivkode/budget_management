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
    NOT_MATCH_USER(HttpStatus.BAD_REQUEST.value(),
            "유저가 일치하지 않습니다."),
    NOT_VALID_DATE(HttpStatus.BAD_REQUEST.value(),
            "유효하지 않은 날짜입니다."),
    NOT_EXIST_EXPENSE(HttpStatus.BAD_REQUEST.value(),
            "지출이 존재하지 않습니다."),
    NOT_EXIST_BUDGET(HttpStatus.BAD_REQUEST.value(),
            "예산이 존재하지 않습니다"),
    UNABLE_TO_SEND_EMAIL(HttpStatus.BAD_REQUEST.value(), "이메일 전송에 실패하였습니다."),
    INTERNAL_SEVER_ERROR(HttpStatus.BAD_REQUEST.value(), "서버 에러 입니다"),
    NOT_FOUND_VERIFY_CODE(HttpStatus.BAD_REQUEST.value(), "인증 코드를 찾을 수 없습니다."),
    INVALID_VERIFY_CODE(HttpStatus.BAD_REQUEST.value(), "유효하지 않은 인증코드 입니다.");

    private final int errorCode;
    private final String errorMessage;

}
