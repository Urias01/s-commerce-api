package com.s.commerce.presentation.cart.controllers;

import com.s.commerce.application.cart.addItem.AddItemToCartRequest;
import com.s.commerce.application.cart.addItem.AddItemToCartResponse;
import com.s.commerce.application.cart.addItem.AddItemToCartUseCase;
import com.s.commerce.application.cart.common.CartItemRequest;
import com.s.commerce.application.commons.response.ApiResponse;
import com.s.commerce.domain.user.valueObject.UserId;
import com.s.commerce.infrastructure.security.AuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final AuthenticatedUser authenticatedUser;

    private final AddItemToCartUseCase addItemToCartUseCase;

    public CartController(AuthenticatedUser authenticatedUser, AddItemToCartUseCase addItemToCartUseCase) {
        this.authenticatedUser = authenticatedUser;
        this.addItemToCartUseCase = addItemToCartUseCase;
    }

    @PostMapping("/items")
    public ResponseEntity<ApiResponse<AddItemToCartResponse>> addItem(@RequestBody CartItemRequest request) {
        UserId customerId = authenticatedUser.getCurrentUserId();

        AddItemToCartResponse response = addItemToCartUseCase.execute(new AddItemToCartRequest(request, customerId));

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }
}
