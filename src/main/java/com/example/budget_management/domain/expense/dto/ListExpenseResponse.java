package com.example.budget_management.domain.expense.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ListExpenseResponse {
    private List<ExpenseResponse> transportList;
    private List<ExpenseResponse> foodList;
    private List<ExpenseResponse> livingList;
    private List<ExpenseResponse> housingList;
    private List<ExpenseResponse> entertainmentList;
    private List<ExpenseResponse> etcList;

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
