package com.example.budget_management.domain.budget;

import com.example.budget_management.domain.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;


    @Builder
    public Budget(Long amount, User user, Category category, LocalDateTime date) {
        this.amount = amount;
        this.user = user;
        this.category = category;
        this.date = date;
    }

    public Budget update(Long amount, LocalDateTime date) {
        this.amount = amount;
        this.date = date;

        return this;
    }
}
