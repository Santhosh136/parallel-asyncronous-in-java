package com.learnjava.service;

import com.learnjava.domain.Inventory;
import com.learnjava.domain.ProductOption;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.delay;

public class InventoryService {
    public Inventory retrieveInventory() {
        delay(500);
        return Inventory.builder()
                .count(2).build();
    }
}
