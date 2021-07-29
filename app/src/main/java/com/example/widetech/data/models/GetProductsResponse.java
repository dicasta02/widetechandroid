package com.example.widetech.data.models;

public class GetProductsResponse {
    private ProductsResponse productResponse;

    public GetProductsResponse() {
        this.productResponse = new ProductsResponse();
    }

    public ProductsResponse getProductResponse() {
        return productResponse;
    }

    public void setProductResponse(ProductsResponse productResponse) {
        this.productResponse = productResponse;
    }
}
