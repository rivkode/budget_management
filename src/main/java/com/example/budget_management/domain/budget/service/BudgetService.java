package com.example.budget_management.domain.budget.service;

import com.example.budget_management.domain.budget.Budget;
import com.example.budget_management.domain.budget.Category;
import com.example.budget_management.domain.budget.dto.request.CreateBudgetRequest;
import com.example.budget_management.domain.budget.dto.request.UpdateBudgetRequest;
import com.example.budget_management.domain.budget.dto.response.CreateOrUpdateBudgetResponse;
import com.example.budget_management.domain.budget.dto.response.GetBudgetInfoResponse;
import com.example.budget_management.domain.budget.repository.BudgetRepository;
import com.example.budget_management.domain.user.User;
import com.example.budget_management.system.common.paging.ListResponse;
import com.example.budget_management.system.exception.CustomErrorCode;
import com.example.budget_management.system.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;


    @Transactional
    public CreateOrUpdateBudgetResponse createBudget(CreateBudgetRequest request, User user) {

        Budget savedBudget = Budget.builder()
                .user(user)
                .amount(request.budget().amount())
                .category(checkCategory(request.categoryName()))
                .build();
        budgetRepository.save(savedBudget);

        return CreateOrUpdateBudgetResponse.from(savedBudget);
    }


    @Transactional
    public CreateOrUpdateBudgetResponse updateBudget(User user, UpdateBudgetRequest request) {
        Budget budget = budgetRepository.findById(request.budgetId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_EXIST_BUDGET));

        // 유저 권한 확인
        if (!budget.getUser().equals(user)) {
            throw new CustomException(CustomErrorCode.NOT_MATCH_USER);
        }
        Budget updatedBudget = budget.update(request.budget().amount(), request.budget().date());

        return CreateOrUpdateBudgetResponse.from(updatedBudget);
    }

    @Transactional(readOnly = true)
    public Budget getBudgetById(Long budgetId) {
        return budgetRepository.findById(budgetId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_EXIST_BUDGET));
    }

//    @Transactional(readOnly = true)
//    public ListResponse<GetBudgetInfoResponse> getBudgetInfoResponse(User user) {
//        Long userId = user.getId();
//
//    }

    public Category checkCategory(String categoryName) {
        return switch (categoryName) {
            case "Transport" -> Category.TRANSPORT;
            case "Food" -> Category.FOOD;
            case "Entertainment" -> Category.ENTERTAINMENT;
            default -> Category.ETC;
        };
    }
}
