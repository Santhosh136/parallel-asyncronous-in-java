package com.learnjava.thread;

import com.learnjava.domain.Product;
import com.learnjava.domain.ProductInfo;
import com.learnjava.domain.Review;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import lombok.Getter;

import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class ProductServiceUsingThreads {
    private final ProductInfoService productInfoService;
    private final ReviewService reviewService;

    public ProductServiceUsingThreads(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) throws InterruptedException {
        stopWatch.start();

        ProductInfoRunnable productInfoRunnable = new ProductInfoRunnable(productId);
        ProductReviewRunnable productReviewRunnable = new ProductReviewRunnable(productId);

        Thread productInfoThread = new Thread(productInfoRunnable);
        Thread productReviewThread = new Thread(productReviewRunnable);

        productInfoThread.start();
        productReviewThread.start();

        productInfoThread.join();
        productReviewThread.join();

        ProductInfo productInfo = productInfoRunnable.getProductInfo();
        Review review = productReviewRunnable.getReview();

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return new Product(productId, productInfo, review);
    }

    public static void main(String[] args) throws InterruptedException {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductServiceUsingThreads productService = new ProductServiceUsingThreads(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }

    private class ProductInfoRunnable implements Runnable {

        private final String productId;
        @Getter
        private ProductInfo productInfo;

        ProductInfoRunnable(String productId) {
            this.productId = productId;
        }

        @Override
        public void run() {
            productInfo = productInfoService.retrieveProductInfo(this.productId);
        }
    }

    private class ProductReviewRunnable implements Runnable {

        private final String productId;
        @Getter
        private Review review;

        ProductReviewRunnable(String productId) {
            this.productId = productId;
        }

        @Override
        public void run() {
            review = reviewService.retrieveReviews(productId);
        }
    }
}
