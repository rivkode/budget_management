package com.example.budget_management.domain.expense;

import java.util.List;

public class CategoryExpenses {
    private List<Expense> expenses;

    public CategoryExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addAll(List<Expense> newExpenses) {
        expenses.addAll(newExpenses);
    }
}
