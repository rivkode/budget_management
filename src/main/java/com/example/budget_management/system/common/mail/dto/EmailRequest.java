package com.example.budget_management.system.common.mail.dto;

import lombok.Builder;

@Builder
public record EmailRequest(
        String recipient,
        String subject,
        String text
) { }