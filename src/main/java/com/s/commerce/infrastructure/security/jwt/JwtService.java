package com.s.commerce.infrastructure.security.jwt;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.s.commerce.domain.user.enums.UserRole;
import com.s.commerce.domain.user.valueObject.UserId;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.s.commerce.config.AppProperties;
import com.s.commerce.domain.common.security.ITokenService;

@Service
public class JwtService implements ITokenService {

  private final AppProperties appProperties;
  private final Algorithm algorithm;

  public JwtService(AppProperties appProperties) {
    this.appProperties = appProperties;
    this.algorithm = Algorithm.HMAC256(appProperties.getJwtSecret());
  }

  public Optional<TokenClaims> extractValidToken(String token) {
    try {
      DecodedJWT decoded = JWT.require(algorithm)
          .withIssuer(appProperties.getJwtIssuer())
          .build()
          .verify(token);

      UserId userId = new UserId(UUID.fromString(decoded.getSubject()));

      return Optional.of(new TokenClaims(userId, decoded.getClaim("role").as(UserRole.class)));

    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public String generateToken(UserId id, UserRole role) {
    return JWT.create()
        .withIssuer(appProperties.getJwtIssuer())
        .withSubject(id.value().toString())
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + appProperties.getJwtExpirationMs()))
        .withClaim("role", role.name())
        .sign(algorithm);
  }

  public record TokenClaims(UserId userId, UserRole role) {
  }

}