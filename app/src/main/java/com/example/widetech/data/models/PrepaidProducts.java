package com.example.widetech.data.models;

public class PrepaidProducts {
    private int responseCode;
    private String responseMsg;
    private String responseDesc;

    public PrepaidProducts(int responseCode, String responseMsg, String responseDesc) {
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
        this.responseDesc = responseDesc;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
