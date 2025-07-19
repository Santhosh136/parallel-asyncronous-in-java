package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.domain.ProductOption;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUsingCompletableFutureTest {

    static ProductServiceUsingCompletableFuture pscf;

    @BeforeAll
    static void setUp() {
        pscf = new ProductServiceUsingCompletableFuture(
                new ProductInfoService(),
                new ReviewService(),
                new InventoryService()
        );
    }

    @Test
    void shouldReturnProduct() {
        String productId = "ABC123";
        Product product = pscf.retrieveProductDetails(productId);

        assertNotNull(product);
        assertEquals(productId, product.getProductId());
        assertFalse(product.getProductInfo().getProductOptions().isEmpty());
    }

    @Test
    void shouldReturnProductAsync() {
        String productId = "ABC123";
        CompletableFuture<Product> productCompletableFuture = pscf.retrieveProductDetailsAsync(productId);

        productCompletableFuture.thenAccept(product -> {
            assertNotNull(product);
            assertEquals(productId, product.getProductId());
            assertFalse(product.getProductInfo().getProductOptions().isEmpty());
        }).join();
    }

    @Test
    void shouldReturnProductWithInventory() {
        String productId = "ABC123";
        Product product = pscf.retrieveProductWithInventory(productId);

        List<ProductOption> productOptions = product.getProductInfo().getProductOptions();

        assertNotNull(product);

        assertEquals(productId, product.getProductId());
        assertFalse(productOptions.isEmpty());
        assertEquals(4, productOptions.size());
        productOptions.forEach(productOption -> assertNotNull(productOption.getInventory()));
    }

}