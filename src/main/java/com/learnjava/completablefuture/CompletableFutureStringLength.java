package com.learnjava.completablefuture;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureStringLength {

    private String addLength(String name) {
        delay(500);
        log("Adding length to string");
        return name + " - " + name.length();
    }

    public CompletableFuture<String> transformString(String name) {
        return CompletableFuture
                .supplyAsync(() -> name)
                .thenApply(this::addLength);

    }
}
