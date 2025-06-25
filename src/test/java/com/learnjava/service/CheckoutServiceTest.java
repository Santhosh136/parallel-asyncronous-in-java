package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    static CheckoutService checkoutService;

    @BeforeAll
    static void setUp() {
        checkoutService = new CheckoutService(new PriceValidatorService());
    }

    @BeforeEach
    void printNoOfCores() {
        System.out.println("No of cores:" + Runtime.getRuntime().availableProcessors());
    }

    @BeforeEach
    void printParallelism() {
        System.out.println("Parallelism:" + ForkJoinPool.getCommonPoolParallelism());
    }

    @Test
    void shouldCheckout() {
        Cart cart = DataSet.createCart(6);
        CheckoutResponse response = checkoutService.checkout(cart);
        assertEquals(CheckoutStatus.SUCCESS, response.getCheckoutStatus());
        assertTrue(response.getFinalRate() > 0);
    }

    @Test
    void shouldNotCheckout() {
        Cart cart = DataSet.createCart(100);
        CheckoutResponse response = checkoutService.checkout(cart);
        assertEquals(CheckoutStatus.FAILURE, response.getCheckoutStatus());
        assertEquals(3, response.getErrorList().size());
    }
}