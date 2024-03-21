package com.example.budget_management.domain.user.service;

import com.example.budget_management.domain.user.User;
import com.example.budget_management.domain.user.dto.request.SignupRequest;
import com.example.budget_management.domain.user.dto.request.VerifyEmailRequest;
import com.example.budget_management.domain.user.dto.response.UserJoinStatusResponse;
import com.example.budget_management.domain.user.repository.UserRepository;
import com.example.budget_management.domain.user.type.JoinStatus;
import com.example.budget_management.system.common.cache.redis.dao.VerifyCode;
import com.example.budget_management.system.common.cache.redis.repository.VerifyCodeRedisRepository;
import com.example.budget_management.system.common.mail.EmailService;
import com.example.budget_management.system.common.mail.dto.EmailWithCodeRequest;
import com.example.budget_management.system.exception.CustomErrorCode;
import com.example.budget_management.system.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordValidation passwordValidation;

    private final EmailService emailService;
    private final VerifyCodeRedisRepository verifyCodeRedisRepository;

    @Transactional
    public UserJoinStatusResponse requestEmailVerify(String email) {

        UserJoinStatusResponse response = checkDuplicatedEmail(email);

        if (JoinStatus.isPossible(response.status())) {
            String subject = "회원가입 이메일 인증 번호";
            String title = "이메일 인증";

            String content = "가입 화면에서 아래 인증번호를 입력해주세요";
            String verifyCode = createVerifyCode();

            EmailWithCodeRequest emailWithCodeRequest = EmailWithCodeRequest.builder()
                    .recipient(email)
                    .subject(subject)
                    .title(title)
                    .content(content)
                    .code(verifyCode)
                    .build();
            emailService.sendEmailWithCode(emailWithCodeRequest);

            // redis에 이메일 인증번호 저장
            verifyCodeRedisRepository.save(
                    VerifyCode.builder()
                            .email(email)
                            .code(verifyCode)
                            .build()
            );
        }

        return response;
    }

    @Transactional
    public UserJoinStatusResponse verifyEmail(VerifyEmailRequest request) {
        UserJoinStatusResponse response = checkDuplicatedEmail(request.email());

        if (JoinStatus.isPossible(response.status())) {
            VerifyCode verifyCode = verifyCodeRedisRepository.findById(request.email())
                    .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_VERIFY_CODE));

            if (!verifyCode.getCode().equals(request.code())) {
                throw new CustomException(CustomErrorCode.INVALID_VERIFY_CODE);
            }

            // 인증 완료 후 코드 삭제
            verifyCodeRedisRepository.deleteById(request.email());
        }

        return response;

    }

    private String createVerifyCode() {
        int length = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }

            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new CustomException(CustomErrorCode.INTERNAL_SEVER_ERROR);
        }
    }

    public void signup(SignupRequest signupRequest) {
        String email = signupRequest.getEmail();
        String password = signupRequest.getPassword();



        // 비밀번호 rule 검증 후 encode
        passwordValidation.validatePassword(signupRequest);
        String encodePassword = passwordEncoder.encode(password);

        User user = User.builder()
                .email(email)
                .password(encodePassword)
                .build();
        userRepository.save(user);
    }

    private UserJoinStatusResponse checkDuplicatedEmail(String email) {
        return userRepository.findFirstByEmail(email)
                .map(user -> UserJoinStatusResponse.builderWithUser()
                        .user(user)
                        .status(JoinStatus.IMPOSSIBLE)
                        .buildWithUser())
                .orElseGet(() -> UserJoinStatusResponse.builderWithOutUser()
                        .status(JoinStatus.POSSIBLE)
                        .buildWithOutUser());
    }

}
