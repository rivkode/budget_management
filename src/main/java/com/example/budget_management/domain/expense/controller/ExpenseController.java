package com.example.budget_management.domain.expense.controller;

import com.example.budget_management.domain.expense.dto.ExpenseRequest;
import com.example.budget_management.domain.expense.dto.ExpenseResponse;
import com.example.budget_management.domain.expense.dto.ListExpenseResponse;
import com.example.budget_management.domain.expense.service.ExpenseService;
import com.example.budget_management.domain.user.User;
import com.example.budget_management.system.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "지출 API", description = "지출 관련된 API 정보를 담고 있습니다.")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Operation(summary = "지출 생성", description = "지출 생성에 필요한 정보를 통해 지출을 생성 합니다.")
    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseService.createExpense(user, request));
    }

    @Operation(summary = "지출 단건 조회", description = "지출 정보를 상세 조회 합니다.")
    @GetMapping(value = "/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpense(@PathVariable("expenseId") Long expenseId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok().body(expenseService.getExpense(user, expenseId));
    }

    @Operation(summary = "지출 전체 조회", description = "지출을 조건에 맞게 카테고리별로 전체 조회 합니다.")
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
