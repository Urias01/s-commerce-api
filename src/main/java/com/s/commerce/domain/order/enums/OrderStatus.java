package com.s.commerce.domain.order.enums;

import lombok.Getter;

import java.util.*;

public enum OrderStatus {

    WAITING_PAYMENT("Esperando Pagamento"),
    APPROVED_PAYMENT("Pagamento aprovado"),
    PENDING("Pendente"),
    PROCESSING("Processando"),
    OUT_FOR_DELIVERY("Saiu para entrega"),
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
        WAITING_PAYMENT.allowedTransitions = EnumSet.of(APPROVED_PAYMENT, CANCELED);
        APPROVED_PAYMENT.allowedTransitions = EnumSet.of(PENDING, CANCELED, REFUNDED);
        PENDING.allowedTransitions = EnumSet.of(PROCESSING, CANCELED);
        PROCESSING.allowedTransitions = EnumSet.of(OUT_FOR_DELIVERY, CANCELED);
        OUT_FOR_DELIVERY.allowedTransitions = EnumSet.of(DELIVERED, CANCELED);
        DELIVERED.allowedTransitions = EnumSet.noneOf(OrderStatus.class);
        REFUNDED.allowedTransitions = EnumSet.noneOf(OrderStatus.class);
        CANCELED.allowedTransitions = EnumSet.noneOf(OrderStatus.class);
    }

    public boolean canTransitionTo(OrderStatus next) {
        return allowedTransitions.contains(next);
    }

    public boolean isTerminal() {
        return allowedTransitions.isEmpty();
    }
}
