package com.s.commerce.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

  private String dbHost;
  private String dbPort;
  private String dbName;
  private String dbUser;
  private String dbPassword;
  private String jwtSecret;
  private String jwtIssuer;
  private Long jwtExpirationMs;

}
