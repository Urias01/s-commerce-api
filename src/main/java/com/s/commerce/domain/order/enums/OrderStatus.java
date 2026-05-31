package com.s.commerce.domain.order.enums;

import lombok.Getter;

import java.util.*;

public enum OrderStatus {

    PENDING("Pendente"),
    APPROVED_PAYMENT("Pagamento aprovado"),
    PROCESSING("Processando"),
    DELIVERED("Entregue"),
    REFUNDED("Reembolso"),
    CANCELED("Cancelado");

    @Getter
    private final String description;

    private Set<OrderStatus> allowedTransitions;

    OrderStatus(String description) {
        this.description = description;
    }

    static {
        PENDING.allowedTransitions         = EnumSet.of(APPROVED_PAYMENT, CANCELED);
        APPROVED_PAYMENT.allowedTransitions = EnumSet.of(PROCESSING, CANCELED, REFUNDED);
        PROCESSING.allowedTransitions       = EnumSet.of(DELIVERED, CANCELED);
        DELIVERED.allowedTransitions        = EnumSet.noneOf(OrderStatus.class);
        REFUNDED.allowedTransitions         = EnumSet.noneOf(OrderStatus.class);
        CANCELED.allowedTransitions         = EnumSet.noneOf(OrderStatus.class);
    }

    public boolean canTransitionTo(OrderStatus next) {
        return allowedTransitions.contains(next);
    }

    public boolean isTerminal() {
        return allowedTransitions.isEmpty();
    }
}
