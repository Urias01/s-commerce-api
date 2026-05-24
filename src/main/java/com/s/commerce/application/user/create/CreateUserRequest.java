package com.s.commerce.application.user.create;

import com.s.commerce.domain.user.valueObject.Email;

public record CreateUserRequest(String name, Email email, String password, String confirmPassword) {

}
