package com.s.commerce.domain.products.valueObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public record Money(BigDecimal value, Currency currency) {

  private static final int SCALE = 2;

  public Money {
    if (value == null) {
      throw new IllegalArgumentException("Value cannot be null");
    }

    if (value.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Value must be positive");
    }

    if (value.scale() > SCALE) {
      throw new IllegalArgumentException("Value cannot have more than " + SCALE + " decimal places");
    }

    value = value.setScale(SCALE, RoundingMode.HALF_EVEN);
  }

  public BigDecimal getAmount() {
    return value;
  }

  public Money add(Money other) {
    assertSameCurrency(other);
    if (other == null) {
      throw new IllegalArgumentException("Other Money cannot be null");
    }

    if (this.value.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Price cannot be negative");
    }

    return new Money(this.value.add(other.value), this.currency);
  }

  public Money subtract(Money other) {
    assertSameCurrency(other);
    if (other == null) {
      throw new IllegalArgumentException("Other Money cannot be null");
    }

    if (this.value.compareTo(other.value) < 0) {
      throw new IllegalArgumentException("Insufficient funds");
    }

    return new Money(this.value.subtract(other.value), this.currency);
  }

  private void assertSameCurrency(Money other) {
    if (!this.currency.equals(other.currency)) {
      throw new IllegalArgumentException("Currencies must match");
    }
  }
}