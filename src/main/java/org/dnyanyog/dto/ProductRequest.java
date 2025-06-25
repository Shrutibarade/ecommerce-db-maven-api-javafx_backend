package org.dnyanyog.dto;

public class ProductRequest {
    private Integer productId; // Change from `int` to `Integer`
    private String productName;
    private int productPrice;
    private int productQuantity;

    // Constructors
    public ProductRequest() {}

    public ProductRequest(Integer productId, String productName, int productPrice, int productQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    // Getters and Setters
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; } // Nullable

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getProductPrice() { return productPrice; }
    public void setProductPrice(int productPrice) { this.productPrice = productPrice; }

    public int getProductQuantity() { return productQuantity; }
    public void setProductQuantity(int productQuantity) { this.productQuantity = productQuantity; }
}
