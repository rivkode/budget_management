package com.example.budget_management.domain.category.service;

import com.example.budget_management.domain.category.dto.CategoryListDto;
import com.example.budget_management.domain.category.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    @Transactional(readOnly = true)
    public CategoryListDto getCategoryList() {
        List<String> categoryNameList = categoryRepository.findCategoryName();

        return CategoryListDto.from(categoryNameList);
    }
}
