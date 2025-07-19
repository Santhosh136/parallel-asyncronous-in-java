package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {

    private static CompletableFutureHelloWorld c;

    @BeforeAll
    static void setUp() {
        c = new CompletableFutureHelloWorld(new HelloWorldService());
    }

    @Test
    void shouldReturnHelloWorldInUpperCase() {
        CompletableFuture<String> cf = c.helloWorld();
        cf.thenAccept(s -> assertEquals("HELLO WORLD", s)).join();
    }

    @Test
    void shouldReturnHelloAndWorld() {
        CompletableFuture<String> cf = c.helloAndWorld();
        cf.thenAccept(s -> assertEquals("HELLO WORLD!", s)).join();
    }
}