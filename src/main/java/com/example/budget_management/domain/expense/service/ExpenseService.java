package com.example.budget_management.domain.expense.service;

import com.example.budget_management.domain.budget.Budget;
import com.example.budget_management.domain.budget.Category;
import com.example.budget_management.domain.budget.service.BudgetService;
import com.example.budget_management.domain.expense.Expense;
import com.example.budget_management.domain.expense.dto.ExpenseRequest;
import com.example.budget_management.domain.expense.dto.ExpenseResponse;
import com.example.budget_management.domain.expense.dto.ListExpenseResponse;
import com.example.budget_management.domain.expense.repository.ExpenseRepository;
import com.example.budget_management.domain.user.User;
import com.example.budget_management.system.exception.CustomErrorCode;
import com.example.budget_management.system.exception.CustomException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public ExpenseResponse createExpense(User user, ExpenseRequest request) {
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

    /**
     * 최소, 최대 금액 param 입력시 해당 조건 적용하여 결과 반환
     * 지출 합계, 카테고리별 지출 합계
     *
     * 카테고리 param 입력하지 않으면 모든 내용 조회
     * 즉, 모든 비용 반환, 카테고리별 비용 반환
     * 기간에 해당하는 모든 expenseResponse를 List로 반환
     *
     * 카테고리 param 입력시 해당 카테고리 기준 조회
     * 즉, 카테고리에 대한하는 지출 합계 반환
     * 기간에 해당하는 모든 expenseResponse 를 List로 반환
     *
     * 위 내용을 카테고리 하나로 특정하면 되지 않나 ?
     *
     * Response 객체를 카테고리별로 나눠야 함
     * @return
     */
    public ListExpenseResponse getListExpense(User user, String categoryName, LocalDateTime startAt,
            LocalDateTime endAt, Long minAmount, Long maxAmount) {
        checkDateStartAndEndAt(startAt, endAt);

        // 카테고리별로 찾는다

        // 카테고리가 입력될 경우 아닐경우 나눔
        // 입력되면 해당 카테고리를 찾아서 반환, 아닐경우 모든 내용 조회하여 반환

        // 해당하는 기간에 모든 지출 반환

        // 해당하는 기간을 뽑아온다 입력으로부터

        // category가 안들어 왔을 경우 모두 반환

        List<Expense> transportList = new ArrayList<>();
        List<Expense> foodList = new ArrayList<>();
        List<Expense> livingList = new ArrayList<>();
        List<Expense> housingList = new ArrayList<>();
        List<Expense> entertainmentList = new ArrayList<>();
        List<Expense> etcList = new ArrayList<>();
        Category[] categories = Category.values();

        for (Category category : categories) {
            List<Expense> expenses = expenseRepository.findAllByUserAndDateAndCategory(user.getId(), startAt, endAt, category.name());

            // 해당 카테고리에 따라서 리스트에 추가
            switch (category) {
                case TRANSPORT -> transportList.addAll(expenses);
                case FOOD -> foodList.addAll(expenses);
                case ENTERTAINMENT -> entertainmentList.addAll(expenses);
                case ETC -> etcList.addAll(expenses);

                // 다른 카테고리가 추가될 경우에 대한 처리도 추가할 수 있습니다.
            }
        }

        return ListExpenseResponse.fromListExpense(transportList, foodList, livingList, housingList, entertainmentList, etcList);
    }

    /**
     * todo
     * start가 end보다 뒤일 경우 예외처리
     * @param startAt
     * @param endAt
     */
    private void checkDateStartAndEndAt(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new CustomException(CustomErrorCode.NOT_VALID_DATE);
        }
    }

    public ExpenseResponse getExpense(User user, Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_EXIST_EXPENSE));

        //check user
        if (!expense.getUser().getEmail().equals(user.getEmail())) {
            log.info(expense.getUser().getEmail());
            log.info(user.getEmail());
            throw new CustomException(CustomErrorCode.NOT_MATCH_USER);
        }

        return ExpenseResponse.from(expense);
    }
}
