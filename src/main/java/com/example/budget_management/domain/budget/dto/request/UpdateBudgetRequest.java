package com.example.budget_management.domain.budget.dto.request;

import jakarta.validation.Valid;

public record UpdateBudgetRequest (
        Long budgetId,
        @Valid
        BudgetAmountRequest budget
) {
}
