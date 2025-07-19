package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.domain.ProductInfo;
import com.learnjava.domain.ProductOption;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

public class ProductServiceUsingCompletableFuture {
    private final ProductInfoService productInfoService;
    private final ReviewService reviewService;
    private final InventoryService inventoryService;

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService, InventoryService inventoryService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
        this.inventoryService = inventoryService;
    }

    public Product retrieveProductDetails(String productId) {
        startTimer();
        Product product = CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
                .thenCombine(CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId)),
                (productInfo, review) -> new Product(productId, productInfo, review))
                .join();
        timeTaken();
        return product;
    }

    public CompletableFuture<Product> retrieveProductDetailsAsync(String productId) {
        return CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
                .thenCombine(CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId)),
                (productInfo, review) -> new Product(productId, productInfo, review));
    }

    public Product retrieveProductWithInventory(String productId) {
        startTimer();
        Product product = CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
                .thenApply(this::updateInventory)
                .thenCombine(CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId)),
                    (productInfo, review) -> new Product(productId, productInfo, review))
                .join();
        timeTaken();
        return product;
    }

    private ProductInfo updateInventory(ProductInfo productInfo) {
        List<ProductOption> productOptions = productInfo.getProductOptions()
                .stream()
                .peek(productOption -> productOption.setInventory(inventoryService.retrieveInventory()))
                .toList();
        productInfo.setProductOptions(productOptions);
        return productInfo;
    }
}
