package com.s.commerce.domain.common.security;

import com.s.commerce.domain.user.enums.UserRole;
import com.s.commerce.domain.user.valueObject.UserId;

public interface ITokenService {

  String generateToken(UserId id, UserRole userRole);

}