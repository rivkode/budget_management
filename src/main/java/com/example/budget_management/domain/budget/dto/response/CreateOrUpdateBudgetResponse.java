package com.example.budget_management.domain.budget.dto.response;

import com.example.budget_management.domain.budget.Budget;

import java.time.LocalDateTime;

public record CreateOrUpdateBudgetResponse(
        Long budgetId,

        Long userId,

        Long amount,

        LocalDateTime endAt,

        String categoryName
){
    public static CreateOrUpdateBudgetResponse from(Budget budget) {
        return new CreateOrUpdateBudgetResponse(
                budget.getId(),
                budget.getUser().getId(),
                budget.getAmount(),
                budget.getDate(),
                budget.getCategory().name()
        );
    }
}
