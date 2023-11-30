package com.example.budget_management.domain.budget.service;

import com.example.budget_management.domain.budget.Budget;
import com.example.budget_management.domain.budget.Category;
import com.example.budget_management.domain.budget.dto.BudgetRequestDto;
import com.example.budget_management.domain.budget.dto.BudgetResponseDto;
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


    public BudgetResponseDto createBudget(BudgetRequestDto requestDto, User user) {
        Budget savedBudget = budgetRepository.save(
                requestDto.toEntity(user, requestDto.getAmount(), checkCategory(requestDto.getCategoryName())));

        return BudgetResponseDto.from(savedBudget);
    }


    public BudgetResponseDto updateBudget(BudgetRequestDto requestDto) {
        Budget budget = budgetRepository.findById(requestDto.getBudgetId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_EXIST_BUDGET));

        Budget updatedBudget = budget.update(requestDto.getAmount(), checkCategory(requestDto.getCategoryName()));

        return BudgetResponseDto.from(updatedBudget);
    }

    private Category checkCategory(String categoryName) {
        if (categoryName.equals("Transport")) {
            return Category.TRANSPORT;
        } else if (categoryName.equals("Food")) {
            return Category.FOOD;
        } else if (categoryName.equals("Living")) {
            return Category.LIVING;
        } else if (categoryName.equals("Housing")) {
            return Category.HOUSING;
        } else if (categoryName.equals("Entertainment")) {
            return Category.ENTERTAINMENT;
        } else {
            return Category.ETC;
        }
    }
}
