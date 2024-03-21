package com.example.budget_management.domain.user.dto.response;

import com.example.budget_management.domain.user.User;
import com.example.budget_management.domain.user.type.JoinStatus;
import lombok.Builder;

import java.time.LocalDateTime;

public record UserJoinStatusResponse(
        String email,
        LocalDateTime createAt,
        String status
) {
    @Builder(buildMethodName = "buildWithUser", builderMethodName = "builderWithUser")
    public UserJoinStatusResponse(User user, JoinStatus status) {
        this(
                user.getEmail(),
                user.getCreateAt(),
                status.getCode()
        );
    }

    @Builder(buildMethodName = "buildWithOutUser", builderMethodName = "builderWithOutUser")
    public UserJoinStatusResponse(JoinStatus status) {
        this(
                null,
                null,
                status.getCode()
        );
    }
}
