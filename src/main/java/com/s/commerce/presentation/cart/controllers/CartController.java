package com.s.commerce.presentation.cart.controllers;

import com.s.commerce.application.cart.addItem.AddItemToCartRequest;
import com.s.commerce.application.cart.addItem.AddItemToCartResponse;
import com.s.commerce.application.cart.addItem.AddItemToCartUseCase;
import com.s.commerce.application.cart.checkOut.CheckOutCartRequest;
import com.s.commerce.application.cart.checkOut.CheckOutCartResponse;
import com.s.commerce.application.cart.checkOut.CheckOutCartUseCase;
import com.s.commerce.application.cart.common.CartItemRequest;
import com.s.commerce.application.cart.removeItem.RemoveItemFromCartRequest;
import com.s.commerce.application.cart.removeItem.RemoveItemFromCartResponse;
import com.s.commerce.application.cart.removeItem.RemoveItemFromCartUseCase;
import com.s.commerce.application.commons.response.ApiResponse;
import com.s.commerce.domain.cart.valueObjects.CartItemId;
import com.s.commerce.domain.user.valueObject.UserId;
import com.s.commerce.infrastructure.security.AuthenticatedUser;
import com.s.commerce.presentation.cart.requests.CheckoutCartHttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final AuthenticatedUser authenticatedUser;

    private final AddItemToCartUseCase addItemToCartUseCase;
    private final RemoveItemFromCartUseCase removeItemFromCartUseCase;
    private final CheckOutCartUseCase checkOutCartUseCase;

    public CartController(
            AuthenticatedUser authenticatedUser,
            AddItemToCartUseCase addItemToCartUseCase,
            RemoveItemFromCartUseCase removeItemFromCartUseCase,
            CheckOutCartUseCase checkOutCartUseCase) {
        this.authenticatedUser = authenticatedUser;
        this.addItemToCartUseCase = addItemToCartUseCase;
        this.removeItemFromCartUseCase = removeItemFromCartUseCase;
        this.checkOutCartUseCase = checkOutCartUseCase;
    }

    @PostMapping("/items")
    public ResponseEntity<ApiResponse<AddItemToCartResponse>> addItem(@RequestBody CartItemRequest request) {
        UserId customerId = authenticatedUser.getCurrentUserId();

        AddItemToCartResponse response = addItemToCartUseCase.execute(new AddItemToCartRequest(request, customerId));

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<ApiResponse<RemoveItemFromCartResponse>> removeItem(@PathVariable CartItemId id) {
        UserId customerId = authenticatedUser.getCurrentUserId();

        RemoveItemFromCartRequest request = new RemoveItemFromCartRequest(id, customerId);
        RemoveItemFromCartResponse response = this.removeItemFromCartUseCase.execute(request);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("/checkout")
    public ResponseEntity<ApiResponse<CheckOutCartResponse>> checkout(@RequestBody CheckoutCartHttpRequest httpRequest) {
        UserId customerId = authenticatedUser.getCurrentUserId();
        CheckOutCartRequest request = new CheckOutCartRequest(httpRequest.cartItemIdList(), httpRequest.scheduledDeliveryDate(), customerId);

        CheckOutCartResponse response = this.checkOutCartUseCase.execute(request);

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
