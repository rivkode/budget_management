package com.example.budget_management.domain.expense.repository;

import com.example.budget_management.domain.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
