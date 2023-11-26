package com.example.budget_management.domain.user.service;

import com.example.budget_management.domain.user.dto.SignupDto;
import com.example.budget_management.system.exception.CustomErrorCode;
import com.example.budget_management.system.exception.CustomException;
import java.util.regex.Pattern;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PasswordValidation {

    public void validatePassword(SignupDto signupDto) {
        String email = signupDto.getEmail();
        String password = signupDto.getPassword();

        // 숫자, 문자, 특수문자 중 1가지 이상 포함
        if (!rules(password)) {
            throw new CustomException(CustomErrorCode.NOT_FOLLOW_RULES);
        }
    }

    private boolean rules(String password) {
        boolean numberEnglish = Pattern.matches("^(?=.*[0-9]+)(?=.*[a-zA-Z]+).+", password);
        boolean englishSpecialSymbol = Pattern.matches("^(?=.*[a-zA-Z]+)(?=.*[!@#$%^&*]+).+", password);
        boolean specialSymbolsNumber = Pattern.matches("^(?=.*[!@#$%^&*]+)(?=.*[0-9]+).+", password);

        return numberEnglish || englishSpecialSymbol || specialSymbolsNumber;
    }

}
