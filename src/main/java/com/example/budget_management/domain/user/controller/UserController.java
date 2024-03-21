package com.example.budget_management.domain.user.controller;

import com.example.budget_management.domain.user.dto.request.SignupRequest;
import com.example.budget_management.domain.user.dto.request.VerifyEmailRequest;
import com.example.budget_management.domain.user.dto.response.UserJoinStatusResponse;
import com.example.budget_management.domain.user.service.UserService;
import com.example.budget_management.system.common.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
@Tag(name = "사용자 API", description = "사용자 관련된 API 정보를 담고 있습니다.")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입에 필요한 정보를 통해 가입을 진행합니다")
    @PostMapping(value = "/signup")
    public ResponseEntity<ApiResponseDto> signup(@RequestBody SignupRequest signupRequest) {
        userService.signup(signupRequest);

        return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.CREATED.value(), "회원 가입 성공"));
    }

    @PostMapping(value = "/request-verify")
    public ResponseEntity<UserJoinStatusResponse> requestEmailVerify(
            @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "이메일 형식에 맞지 않습니다.")
            @RequestParam(value = "email") String email
    ) {
        return ResponseEntity.ok(userService.requestEmailVerify(email));
    }

    @PostMapping(value = "/verify-email")
    public ResponseEntity<UserJoinStatusResponse> verifyEmail(@RequestBody @Valid VerifyEmailRequest request) {
        return ResponseEntity.ok(userService.verifyEmail(request));
    }
}
