package com.example.budget_management.domain.budget.repository;

import com.example.budget_management.domain.budget.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

}
