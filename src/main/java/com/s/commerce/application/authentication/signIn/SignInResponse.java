package com.s.commerce.application.authentication.signIn;

public record SignInResponse(String token, Long expiration) {
}
