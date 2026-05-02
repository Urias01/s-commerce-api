package com.s.commerce.infrastructure.security.jwt;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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

      UUID userId = UUID.fromString(decoded.getSubject());

      return Optional.of(new TokenClaims(userId, decoded.getClaim("role").asString()));

    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public String generateToken(UUID userId, String role) {
    return JWT.create()
        .withIssuer(appProperties.getJwtIssuer())
        .withSubject(userId.toString())
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(appProperties.getJwtExpirationMs())))
        .withClaim("role", role)
        .sign(algorithm);
  }

  public record TokenClaims(UUID userId, String role) {
  }

}