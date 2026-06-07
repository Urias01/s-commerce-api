package com.s.commerce.infrastructure.security;

import com.s.commerce.domain.user.valueObject.UserId;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {

    public UserId getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        return (UserId) auth.getPrincipal();
    }
}
