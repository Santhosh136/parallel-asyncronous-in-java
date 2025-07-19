package com.learnjava.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HPSortingCeremony {

    private final HPSortingHat hpSortingHat;

    public HPSortingCeremony(HPSortingHat hpSortingHat) {
        this.hpSortingHat = hpSortingHat;
    }

    public List<String> sortHouses(List<String> personalities) {
        CompletableFuture<String> h1 = CompletableFuture.supplyAsync(() -> hpSortingHat.getHouse(personalities.get(0)));
        CompletableFuture<String> h2 = CompletableFuture.supplyAsync(() -> hpSortingHat.getHouse(personalities.get(1)));
        CompletableFuture<String> h3 = CompletableFuture.supplyAsync(() -> hpSortingHat.getHouse(personalities.get(2)));
        CompletableFuture<String> h4 = CompletableFuture.supplyAsync(() -> hpSortingHat.getHouse(personalities.get(3)));

        return h1.thenCombine(h2, (house1, house2) -> {
            List<String> houses = new ArrayList<>();
            houses.add(house1);
            houses.add(house2);
            return houses;
        }).thenCombine(h3, (houses, houses3) -> {
            houses.add(houses3);
            return houses;
        }).thenCombine(h4, (houses, house4) -> {
            houses.add(house4);
            return houses;
        }).join();
    }

}
