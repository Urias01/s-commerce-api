package com.s.commerce.application.user.mappers;

import com.s.commerce.application.user.create.CreateUserRequest;
import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.enums.UserRole;
import com.s.commerce.domain.user.valueObject.HashedPassword;

public class UserMapper {

    public static User toEntity(CreateUserRequest request, HashedPassword password) {
        return new User(request.name(), request.email(), password, UserRole.CUSTOMER);
    }
}
