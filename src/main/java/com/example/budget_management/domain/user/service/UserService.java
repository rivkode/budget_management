package com.example.budget_management.domain.user.service;

import com.example.budget_management.domain.user.User;
import com.example.budget_management.domain.user.dto.SignupDto;
import com.example.budget_management.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public void signup(SignupDto signupDto) {
        String email = signupDto.getEmail();
        String password = signupDto.getPassword();

        // email로 중복 체크
        getUser(email);

        // 비밀번호 rule 검증 후 encode
        passwordValidation.validatePassword(signupDto);
        String encodePassword = passwordEncoder.encode(password);

        User user = User.builder()
                .email(email)
                .password(encodePassword)
                .build();
        userRepository.save(user);
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Not found" + email));
    }

}
