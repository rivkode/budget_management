package com.example.budget_management.domain.budget.dto;

import com.example.budget_management.domain.budget.Budget;
import com.example.budget_management.domain.budget.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class BudgetResponseDto {
    private long budgetId;
    private long userId;
    private long amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss.SSS", timezone = "Asia/Seoul")
    private LocalDateTime endAt;
    private Category category;

    public static BudgetResponseDto from(Budget budget) {
        return new BudgetResponseDto(budget.getId(), budget.getUser().getId(), budget.getAmount(),
                budget.getMonth(), budget.getCategory());
    }
}
