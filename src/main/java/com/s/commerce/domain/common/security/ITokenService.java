package com.s.commerce.domain.common.security;

import java.util.UUID;

public interface ITokenService {

  String generateToken(UUID userId, String userRole);

}