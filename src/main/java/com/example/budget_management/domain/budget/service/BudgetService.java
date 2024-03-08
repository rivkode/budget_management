package com.example.budget_management.domain.budget.service;

import com.example.budget_management.domain.budget.Budget;
import com.example.budget_management.domain.budget.Category;
import com.example.budget_management.domain.budget.dto.BudgetRequest;
import com.example.budget_management.domain.budget.dto.BudgetResponse;
import com.example.budget_management.domain.budget.repository.BudgetRepository;
import com.example.budget_management.domain.user.User;
import com.example.budget_management.system.exception.CustomErrorCode;
import com.example.budget_management.system.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BudgetService {

    private final BudgetRepository budgetRepository;


    public BudgetResponse createBudget(BudgetRequest request, User user) {
        Budget savedBudget = budgetRepository.save(
                request.toEntity(user, request, checkCategory(request.getCategoryName())));

        return BudgetResponse.from(savedBudget);
    }


    public BudgetResponse updateBudget(User user, BudgetRequest request) {
        Budget budget = budgetRepository.findById(request.getBudgetId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_EXIST_BUDGET));

        if (!budget.getUser().equals(user)) {
            throw new CustomException(CustomErrorCode.NOT_MATCH_USER);
        }

        Budget updatedBudget = budget.update(request.getAmount(), checkCategory(request.getCategoryName()));

        return BudgetResponse.from(updatedBudget);
    }

    public Budget getBudgetById(Long budgetId) {
        return budgetRepository.findById(budgetId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_EXIST_BUDGET));
    }

    public Category checkCategory(String categoryName) {
        return switch (categoryName) {
            case "Transport" -> Category.TRANSPORT;
            case "Food" -> Category.FOOD;
            case "Living" -> Category.LIVING;
            case "Housing" -> Category.HOUSING;
            case "Entertainment" -> Category.ENTERTAINMENT;
            default -> Category.ETC;
        };
    }
}
