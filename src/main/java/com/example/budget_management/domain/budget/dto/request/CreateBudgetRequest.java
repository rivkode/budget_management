package com.example.budget_management.domain.budget.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateBudgetRequest (
        @NotNull
        Long amount,

        Long budgetId,

        String categoryName,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss.SSS", timezone = "Asia/Seoul")
        LocalDateTime endAt
) {}
