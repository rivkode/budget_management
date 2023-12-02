package com.example.budget_management.domain.expense.repository;

import com.example.budget_management.domain.expense.Expense;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(value = "SELECT * "
            + "FROM expense "
            + "WHERE user_id = :userId "
            + "AND expense_date BETWEEN :startAt AND :endAt "
            + "AND category = UPPER(:category)", nativeQuery = true)
    List<Expense> findAllByUserAndDateAndCategory(@Param("userId") Long userId, @Param("startAt")LocalDateTime startAt, @Param("endAt")LocalDateTime endAt, @Param("category") String category);
}
