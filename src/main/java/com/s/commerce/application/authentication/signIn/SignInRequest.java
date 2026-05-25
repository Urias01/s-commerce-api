package com.s.commerce.application.authentication.signIn;

import com.s.commerce.domain.user.valueObject.Email;

public record SignInRequest(Email email, String password) {
}
