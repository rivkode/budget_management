package com.example.budget_management.domain.budget.controller;

import com.example.budget_management.domain.budget.dto.BudgetRequest;
import com.example.budget_management.domain.budget.dto.BudgetResponse;
import com.example.budget_management.domain.budget.service.BudgetService;
import com.example.budget_management.domain.user.User;
import com.example.budget_management.system.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<BudgetResponse> createBudget(@RequestBody BudgetRequest requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userDetails.getUser();
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetService.createBudget(requestDto, user));
    }

    @PutMapping
    public ResponseEntity<BudgetResponse> updateBudget(@RequestBody BudgetRequest requestDto) {
        return ResponseEntity.ok().body(budgetService.updateBudget(requestDto));
    }

}
