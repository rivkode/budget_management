package com.example.budget_management.domain.expense.service;

import com.example.budget_management.domain.budget.Budget;
import com.example.budget_management.domain.budget.Category;
import com.example.budget_management.domain.budget.service.BudgetService;
import com.example.budget_management.domain.expense.Expense;
import com.example.budget_management.domain.expense.dto.ExpenseRequest;
import com.example.budget_management.domain.expense.dto.ExpenseResponse;
import com.example.budget_management.domain.expense.repository.ExpenseRepository;
import com.example.budget_management.domain.user.User;
import com.example.budget_management.system.exception.CustomErrorCode;
import com.example.budget_management.system.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final BudgetService budgetService;

    public ExpenseResponse createExpense(ExpenseRequest request, User user) {
        Budget budget = budgetService.getBudgetById(request.getBudgetId());

        //check user
        if (!budget.getUser().getEmail().equals(user.getEmail())) {
            log.info(budget.getUser().getEmail());
            log.info(user.getEmail());
            throw new CustomException(CustomErrorCode.NOT_MATCH_USER);
        }

        Category category = budgetService.checkCategory(request.getCategoryName());
        Expense savedExpense = expenseRepository.save(request.toEntity(user, budget, category));

        return ExpenseResponse.from(savedExpense);
    }
}
