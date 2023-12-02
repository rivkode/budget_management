package com.example.budget_management.domain.expense.dto;

import com.example.budget_management.domain.expense.Expense;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ListExpenseResponse {
    private Long expenseTotal;
    private Long transportListTotal;
    private Long foodListTotal;
    private Long livingListTotal;
    private Long housingListTotal;
    private Long entertainmentListTotal;
    private Long etcListTotal;

    private List<ExpenseResponse> transportList;
    private List<ExpenseResponse> foodList;
    private List<ExpenseResponse> livingList;
    private List<ExpenseResponse> housingList;
    private List<ExpenseResponse> entertainmentList;
    private List<ExpenseResponse> etcList;

    public static ListExpenseResponse fromListExpense(List<Expense> transportList, List<Expense> foodList,
            List<Expense> livingList, List<Expense> housingList,
            List<Expense> entertainmentList, List<Expense> etcList
            ) {

        // 해당 카테고리에 따라서 리스트에 추가
        return ListExpenseResponse.builder()
                .transportList(convertToExpenseResponseList(transportList))
                .foodList(convertToExpenseResponseList(foodList))
                .livingList(convertToExpenseResponseList(livingList))
                .housingList(convertToExpenseResponseList(housingList))
                .entertainmentList(convertToExpenseResponseList(entertainmentList))
                .etcList(convertToExpenseResponseList(etcList))
                .expenseTotal(
                        getTotal(transportList) + getTotal(foodList) + getTotal(livingList)
                        + getTotal(housingList) + getTotal(entertainmentList) + getTotal(etcList)
                )
                .transportListTotal(getTotal(transportList))
                .foodListTotal(getTotal(foodList))
                .livingListTotal(getTotal(livingList))
                .housingListTotal(getTotal(housingList))
                .entertainmentListTotal(getTotal(entertainmentList))
                .etcListTotal(getTotal(etcList))
                .build();
    }

    private static List<ExpenseResponse> convertToExpenseResponseList(List<Expense> expenses) {
        List<ExpenseResponse> expenseResponses = new ArrayList<>();
        for (Expense expense : expenses) {
            expenseResponses.add(ExpenseResponse.from(expense));
        }
        return expenseResponses;
    }

    private static Long getTotal(List<Expense> expenseResponses) {
        return expenseResponses.stream()
                .mapToLong(Expense::getAmount)
                .sum();
    }

    public static ListExpenseResponse from(List<ExpenseResponse> transportList, List<ExpenseResponse> foodList,
            List<ExpenseResponse> livingList, List<ExpenseResponse> housingList, List<ExpenseResponse> entertainmentList,
            List<ExpenseResponse> etcList) {
        return ListExpenseResponse.builder()
                .transportList(transportList)
                .foodList(foodList)
                .livingList(livingList)
                .housingList(housingList)
                .entertainmentList(entertainmentList)
                .etcList(etcList)
                .build();
    }
}
