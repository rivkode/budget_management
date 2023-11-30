package com.example.budget_management.domain.expense.dto;

import com.example.budget_management.domain.budget.Category;
import com.example.budget_management.domain.expense.Expense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ExpenseResponse {
    private long amount;
    private Long expenseId;
    private Category category;

    public static ExpenseResponse from(Expense expense) {
        return ExpenseResponse.builder()
                .amount(expense.getAmount())
                .expenseId(expense.getId())
                .category(expense.getCategory())
                .build();
    }

}
