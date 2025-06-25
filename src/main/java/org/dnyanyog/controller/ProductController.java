package org.dnyanyog.controller;

import org.dnyanyog.dto.ProductRequest;
import org.dnyanyog.dto.ProductResponse;
import org.dnyanyog.entity.Product;
import org.dnyanyog.repo.ProductRepository;
import org.dnyanyog.service.ProductService;  // ✅ Import ProductService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    // ✅ Add ProductService instance
    
    @Autowired
    private ProductRepository productRepository;

    // Create a new product
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.createProduct(productRequest);
        return ResponseEntity.ok(response);
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    

    @PostMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProduct(
            @RequestBody ProductRequest request) {

        // 🔹 Validate that both fields are provided
        if (request.getProductId() == null || request.getProductName() == null || 
            request.getProductName().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Collections.emptyList()); // Return an empty list instead of null
        }

        List<ProductResponse> products = productService.searchProduct(
            request.getProductId(), request.getProductName()
        );

        // 🔹 If no products found, return HTTP 404
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(products);
        }

        return ResponseEntity.ok(products);
    }


    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable int productId, 
            @RequestBody ProductRequest productRequest) {

        System.out.println("🔹 Received PUT request for Product ID: " + productId);
        System.out.println("🔹 Request Body: " + productRequest);

        try {
            ProductResponse updatedProduct = productService.updateProduct(productId, productRequest);
            System.out.println("✅ Updated Product: " + updatedProduct);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            System.out.println("❌ Error updating product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    

    // Delete a product
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        Map<String, String> response = new HashMap<>();
        return ResponseEntity.ok("Product deleted successfully");
    }
}
