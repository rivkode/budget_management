package com.example.budget_management.domain.user.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record VerifyEmailRequest (

        @NotNull(message = "이메일은 필수 입력값입니다.")
        @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "이메일 형식에 맞지 않습니다.")
        String email,

        @NotNull(message = "이메일 인증코드는 필수 입력값입니다.")
        @Pattern(regexp = "\\d{6}", message = "이메일 인증코드는 6자리 정수입니다.")
        String code

) {}