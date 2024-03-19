package com.example.budget_management.domain.budget.controller;

import com.example.budget_management.domain.budget.dto.request.CreateBudgetRequest;
import com.example.budget_management.domain.budget.dto.request.UpdateBudgetRequest;
import com.example.budget_management.domain.budget.dto.response.CreateOrUpdateBudgetResponse;
import com.example.budget_management.domain.budget.service.BudgetService;
import com.example.budget_management.domain.user.User;
import com.example.budget_management.system.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "예산 API", description = "예산 관련된 API 정보를 담고 있습니다.")
@RequiredArgsConstructor
@RequestMapping(value = "/api/budgets")
@RestController
public class BudgetController {

    private final BudgetService budgetService;

    /**
     * 예산 생성
     * - 유저와 예산 연관관계 매핑
     * - 카테고리와 날짜를 통해 예산 데이터 생성
     */
    @Operation(summary = "예산 생성", description = "예산 생성에 필요한 정보를 통해 예산을 생성 합니다.")
    @PostMapping
    public ResponseEntity<CreateOrUpdateBudgetResponse> createBudget(@RequestBody @Valid CreateBudgetRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetService.createBudget(request, user));
    }

    /**
     * 예산 수정
     * - 현재 로그인한 유저 조회
     * - 현재 예산에 대해 로그인한 유저의 접근 권한 확인
     * - 예산 항목 수정
     */
    @Operation(summary = "예산 수정", description = "예산 수정에 필요한 정보를 통해 예산을 수정 합니다.")
    @PutMapping
    public ResponseEntity<CreateOrUpdateBudgetResponse> updateBudget(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody UpdateBudgetRequest request
    ) {
        User user = userDetails.getUser();
        return ResponseEntity.ok().body(budgetService.updateBudget(user, request));
    }

}
