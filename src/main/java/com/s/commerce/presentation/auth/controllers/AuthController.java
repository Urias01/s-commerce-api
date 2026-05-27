package com.s.commerce.presentation.auth.controllers;

import com.s.commerce.application.authentication.signIn.SignInRequest;
import com.s.commerce.application.authentication.signIn.SignInResponse;
import com.s.commerce.application.authentication.signIn.SignInUseCase;
import com.s.commerce.application.commons.response.ApiResponse;
import com.s.commerce.application.user.create.CreateUserRequest;
import com.s.commerce.application.user.create.CreateUserResponse;
import com.s.commerce.application.user.create.CreateUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/auth")
public class AuthController {

    private final CreateUserUseCase createUserUseCase;
    private final SignInUseCase signInUseCase;

    public AuthController(CreateUserUseCase createUserUseCase, SignInUseCase signInUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.signInUseCase = signInUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<CreateUserResponse>> createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response = this.createUserUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @PostMapping("/signing")
    public ResponseEntity<ApiResponse<SignInResponse>> signIn(@RequestBody SignInRequest request) {
        SignInResponse response = this.signInUseCase.execute(request);
        return ResponseEntity.ok().body(ApiResponse.success(response));
    }

}
