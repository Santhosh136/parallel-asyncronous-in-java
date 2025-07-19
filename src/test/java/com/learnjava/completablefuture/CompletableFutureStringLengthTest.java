package com.learnjava.completablefuture;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureStringLengthTest {

    private static CompletableFutureStringLength cfsl;

    @BeforeAll
    static void setUp() {
        cfsl = new CompletableFutureStringLength();
    }


    @Test
    void shouldReturnStringLengthAsync() {
        CompletableFuture<String> cf =  cfsl.transformString("Harry");
        cf.thenAccept(s -> {
            assertEquals("Harry - 5", s);
        }).join();
    }

}