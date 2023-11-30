package com.example.budget_management.domain.expense.dto;

import com.example.budget_management.domain.budget.Budget;
import com.example.budget_management.domain.budget.Category;
import com.example.budget_management.domain.expense.Expense;
import com.example.budget_management.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExpenseRequest {
    private long amount;
    private Long budgetId;
    private Long expenseId;
    private String categoryName;

    public Expense toEntity(User user, Budget budget, Category category) {
        return Expense.builder()
                .amount(amount)
                .category(category)
                .budget(budget)
                .user(user)
                .build();
    }

}
