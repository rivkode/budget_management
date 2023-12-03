package com.example.budget_management.domain.user.controller;

import com.example.budget_management.domain.user.dto.SignupRequest;
import com.example.budget_management.domain.user.service.UserService;
import com.example.budget_management.system.common.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
