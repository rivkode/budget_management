package com.example.budget_management.domain.budget.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BudgetAmountRequest (
    @NotNull
    Long amount,

    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss.SSS", timezone = "Asia/Seoul")
    LocalDateTime date
){
}
