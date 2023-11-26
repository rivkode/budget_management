package com.example.budget_management.domain.category.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CategoryListDto {
    private List<String> categoryList;

    public static CategoryListDto from(List<String> categoryList) {
        return CategoryListDto.builder()
                .categoryList(categoryList)
                .build();
    }


}
