package com.example.budget_management.domain.budget.dto.request;

import jakarta.validation.Valid;


public record CreateBudgetRequest (

        String categoryName,

        @Valid
        BudgetAmountRequest budget
) {
}
