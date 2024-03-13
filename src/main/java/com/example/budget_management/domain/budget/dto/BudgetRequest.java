package com.example.budget_management.domain.budget.dto;

import com.example.budget_management.domain.budget.Budget;
import com.example.budget_management.domain.budget.Category;
import com.example.budget_management.domain.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BudgetRequest {
    @NotNull
    private long amount;
    private Long budgetId;
    private String categoryName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss.SSS", timezone = "Asia/Seoul")
    private LocalDateTime endAt;

    public Budget toEntity(User user, BudgetRequest request, Category category) {
        return Budget.builder()
                .amount(request.getAmount())
                .user(user)
                .month(request.endAt)
                .category(category)
                .build();
    }
}
