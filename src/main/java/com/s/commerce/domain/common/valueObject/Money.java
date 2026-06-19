package com.s.commerce.domain.common.valueObject;

import com.fasterxml.jackson.annotation.JsonValue;
import com.s.commerce.domain.common.exceptions.InvalidArgumentException;
import com.s.commerce.domain.common.exceptions.InvalidOperationException;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Embeddable
public record Money(@JsonValue  BigDecimal amount) {

    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING = RoundingMode.HALF_EVEN;

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money {
        if (amount == null)
            throw new InvalidArgumentException("Amount cannot be null");
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidArgumentException("Amount cannot be negative");

        amount = amount.setScale(SCALE, ROUNDING);
    }

    public static Money of(String amount) {
        return new Money(new BigDecimal(amount));
    }

    public static Money of(BigDecimal amount) {
        return new Money(amount);
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        BigDecimal result = this.amount.subtract(other.amount);
        if (result.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidOperationException(
                    "Subtraction would result in negative amount: %s - %s"
                            .formatted(this.amount, other.amount)
            );
        return new Money(result);
    }

    public Money multiply(int quantity) {
        if (quantity < 0)
            throw new InvalidArgumentException("Quantity cannot be negative");
        return new Money(this.amount.multiply(BigDecimal.valueOf(quantity)));
    }

    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }

    public boolean isLessThan(Money other) {
        return this.amount.compareTo(other.amount) < 0;
    }

    public boolean isZero() {
        return this.amount.compareTo(BigDecimal.ZERO) == 0;
    }

    @Override
    public String toString() {
        return "BRL " + amount.toPlainString();
    }
}