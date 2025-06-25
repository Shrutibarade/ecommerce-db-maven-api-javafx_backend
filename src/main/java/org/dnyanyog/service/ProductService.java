package org.dnyanyog.service;

import org.dnyanyog.dto.ProductRequest;
import org.dnyanyog.dto.ProductResponse;
import org.dnyanyog.entity.Product;
import org.dnyanyog.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Create a new product
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product(
                productRequest.getProductName(),
                productRequest.getProductPrice(),
                productRequest.getProductQuantity()
        );

        Product savedProduct = productRepository.save(product);

        return new ProductResponse(
                savedProduct.getProductId(),
                savedProduct.getProductName(),
                savedProduct.getProductPrice(),
                savedProduct.getProductQuantity()
        );
    }

    // Get all products
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductResponse(
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        product.getProductQuantity()
                ))
                .toList();
    }

   

    
    
    public List<ProductResponse> searchProduct(Integer productId, String productName) {
        List<Product> productList;

        if (productId != null && productName != null) {
            productList = productRepository.findByProductIdAndProductName(productId, productName);
        } else {
            return List.of(); // Return empty if both fields are not provided
        }

        return productList.stream()
                .map(product -> new ProductResponse(
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        product.getProductQuantity()
                ))
                .collect(Collectors.toList());
    
    }
    

    public ProductResponse updateProduct(int productId, ProductRequest productRequest) {
        System.out.println("🔹 Checking database for Product ID: " + productId);

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("❌ Product not found in DB!"));

        System.out.println("✅ Found Product: " + existingProduct);

        // Update product details
        existingProduct.setProductName(productRequest.getProductName());
        existingProduct.setProductPrice(productRequest.getProductPrice());
        existingProduct.setProductQuantity(productRequest.getProductQuantity());

        Product updatedProduct = productRepository.save(existingProduct);
        System.out.println("✅ Product Updated Successfully: " + updatedProduct);

        return new ProductResponse(
                updatedProduct.getProductId(),
                updatedProduct.getProductName(),
                updatedProduct.getProductPrice(),
                updatedProduct.getProductQuantity()
        );
    }
    

    


    // Delete a product
    public void deleteProduct(int productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found with ID: " + productId);
        }
        productRepository.deleteById(productId);
    }
}
