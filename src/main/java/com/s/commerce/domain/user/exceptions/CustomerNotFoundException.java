package com.s.commerce.domain.user.exceptions;

import com.s.commerce.domain.common.exceptions.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {
    public CustomerNotFoundException() {
        super("Customer not found!");
    }
}
