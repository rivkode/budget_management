package com.example.budget_management.domain.budget.controller;

import com.example.budget_management.domain.budget.dto.BudgetRequest;
import com.example.budget_management.domain.budget.dto.BudgetResponse;
import com.example.budget_management.domain.budget.service.BudgetService;
import com.example.budget_management.domain.user.User;
import com.example.budget_management.system.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "예산 API", description = "예산 관련된 API 정보를 담고 있습니다.")
public class BudgetController {

    private final BudgetService budgetService;

    @Operation(summary = "예산 생성", description = "예산 생성에 필요한 정보를 통해 예산을 생성 합니다.")
    @PostMapping
    public ResponseEntity<BudgetResponse> createBudget(@RequestBody BudgetRequest requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userDetails.getUser();
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetService.createBudget(requestDto, user));
    }

    @Operation(summary = "예산 수정", description = "예산 수정에 필요한 정보를 통해 예산을 수정 합니다.")
    @PutMapping
    public ResponseEntity<BudgetResponse> updateBudget(@RequestBody BudgetRequest requestDto) {
        return ResponseEntity.ok().body(budgetService.updateBudget(requestDto));
    }

}
