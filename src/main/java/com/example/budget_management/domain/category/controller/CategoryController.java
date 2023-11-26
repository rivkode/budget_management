package com.example.budget_management.domain.category.controller;

import com.example.budget_management.domain.category.dto.CategoryListDto;
import com.example.budget_management.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryListDto> getCategoryList() {

        return ResponseEntity.ok().body(categoryService.getCategoryList());
    }

}
