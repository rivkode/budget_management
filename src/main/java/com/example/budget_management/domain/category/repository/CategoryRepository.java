package com.example.budget_management.domain.category.repository;

import com.example.budget_management.domain.category.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT name FROM category", nativeQuery = true)
    List<String> findCategoryName();
}
