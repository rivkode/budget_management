package com.example.budget_management.domain.user.service;

import com.example.budget_management.domain.user.User;
import com.example.budget_management.domain.user.dto.SignupRequest;
import com.example.budget_management.domain.user.repository.UserRepository;
import com.example.budget_management.system.exception.CustomErrorCode;
import com.example.budget_management.system.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordValidation passwordValidation;

    public void signup(SignupRequest signupRequest) {
        String email = signupRequest.getEmail();
        String password = signupRequest.getPassword();

        // email로 중복 체크
        User findUser = findUserByEmail(email);
        if (findUser != null) {
            throw new CustomException(CustomErrorCode.USER_ALREADY_EXIST);
        }

        // 비밀번호 rule 검증 후 encode
        passwordValidation.validatePassword(signupRequest);
        String encodePassword = passwordEncoder.encode(password);

        User user = User.builder()
                .email(email)
                .password(encodePassword)
                .build();
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

}
