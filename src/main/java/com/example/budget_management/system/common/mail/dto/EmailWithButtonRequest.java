package com.example.budget_management.system.common.mail.dto;

import lombok.Builder;

@Builder
public record EmailWithButtonRequest(
        String recipient,
        String subject,
        String title,
        String content,
        String buttonText,
        String buttonUrl
) {
}