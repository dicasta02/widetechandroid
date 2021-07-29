package com.example.widetech.data.models;

public class ProductsResponse {

    private PrepaidProducts prepaidProducts;
    private Records records;

    public PrepaidProducts getPrepaidAccount() {
        return prepaidProducts;
    }

    public void setPrepaidAccount(PrepaidProducts prepaidAccount) {
        this.prepaidProducts = prepaidAccount;
    }

    public Records getRecords() {
        return records;
    }

    public void setRecords(Records records) {
        this.records = records;
    }
}
