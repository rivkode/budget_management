package com.example.budget_management.domain.budget.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateBudgetResponse (
        Long budgetId,

        Long userId,

        Long amount,

        LocalDateTime endAt,

        String categoryName
){
}
