package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CartItem;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;

import java.util.List;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CheckoutService {

    PriceValidatorService priceValidatorService;

    public CheckoutService(PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }

    public CheckoutResponse checkout(Cart cart) {
        startTimer();
        List<CartItem> expiredItems = cart.getCartItemList()
                .parallelStream()
                .peek(cartItem -> {
                    boolean isPriceInvalid = priceValidatorService.isCartItemInvalid(cartItem);
                    cartItem.setExpired(isPriceInvalid);
                })
                .filter(CartItem::isExpired)
                .toList();
        timeTaken();
        resetTimer();

        if (!expiredItems.isEmpty()) {
            return new CheckoutResponse(CheckoutStatus.FAILURE, expiredItems);
        }

        double totalCost = getTotalCost(cart);
        log("Total cost: " + totalCost);

        return new CheckoutResponse(CheckoutStatus.SUCCESS, totalCost);
    }

    public double getTotalCost(Cart cart) {
        return cart.getCartItemList()
                .stream()
                .map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
                .reduce(0.0, Double::sum);
    }
}
