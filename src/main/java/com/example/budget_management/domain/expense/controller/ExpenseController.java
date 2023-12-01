package com.example.budget_management.domain.expense.controller;

import com.example.budget_management.domain.expense.dto.ExpenseRequest;
import com.example.budget_management.domain.expense.dto.ExpenseResponse;
import com.example.budget_management.domain.expense.dto.ListExpenseResponse;
import com.example.budget_management.domain.expense.service.ExpenseService;
import com.example.budget_management.domain.user.User;
import com.example.budget_management.system.security.UserDetailsImpl;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseService.createExpense(user, request));
    }

    @GetMapping(value = "/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpense(@PathVariable("expenseId") Long expenseId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok().body(expenseService.getExpense(user, expenseId));
    }

    @GetMapping
    public ResponseEntity<ListExpenseResponse> getListExpense(
            @RequestParam(required = false) String categoryName,
            @RequestParam(name = "startAt")LocalDateTime startAt,
            @RequestParam(name = "endAt")LocalDateTime endAt,
            @RequestParam(name = "minAmount", required = false) Long minAmount,
            @RequestParam(name = "maxAmount", required = false) Long maxAmount,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        User user = userDetails.getUser();
        return ResponseEntity.ok()
                .body(expenseService.getListExpense(user, categoryName, startAt, endAt, minAmount,
                        maxAmount));

    }

}
