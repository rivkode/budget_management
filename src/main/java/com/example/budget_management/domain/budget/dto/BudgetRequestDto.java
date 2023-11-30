package com.example.budget_management.domain.budget.dto;

import com.example.budget_management.domain.budget.Budget;
import com.example.budget_management.domain.budget.Category;
import com.example.budget_management.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BudgetRequestDto {
    private long amount;
    private Long budgetId;
    private String categoryName;

    public Budget toEntity(User user, Long amount, Category category) {
        return Budget.builder()
                .amount(amount)
                .user(user)
                .category(category)
                .build();
    }
}
