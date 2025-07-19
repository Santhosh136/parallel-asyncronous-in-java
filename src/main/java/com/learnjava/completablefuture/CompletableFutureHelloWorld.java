package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureHelloWorld {

    private final HelloWorldService helloWorldService;

    public CompletableFutureHelloWorld(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture
                .supplyAsync(helloWorldService::helloWorld) // responsive nature
                .thenApply(String::toUpperCase);
    }

    public CompletableFuture<String> helloAndWorld() {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);

        return hello
                .thenCombine(world, (helloString, worldString) -> helloString + worldString)
                .thenApply(String::toUpperCase);

    }
}
