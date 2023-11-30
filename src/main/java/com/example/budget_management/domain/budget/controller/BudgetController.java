package com.example.budget_management.domain.budget.controller;

import com.example.budget_management.domain.budget.dto.BudgetRequestDto;
import com.example.budget_management.domain.budget.dto.BudgetResponseDto;
import com.example.budget_management.domain.budget.service.BudgetService;
import com.example.budget_management.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    public ResponseEntity<BudgetResponseDto> createBudget(
            @RequestBody BudgetRequestDto requestDto) {

        User user;
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetService.createBudget(requestDto, user));
    }

    @PutMapping
    public ResponseEntity<BudgetResponseDto> updateBudget(
            @RequestBody BudgetRequestDto requestDto) {
        return ResponseEntity.ok().body(budgetService.updateBudget(requestDto));
    }

}
