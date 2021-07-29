package com.example.widetech.data.models;

import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("response_code")
    private int responseCode;
    @SerializedName("response_msg")
    private String responseMsg;

    public Response(int responseCode, String responseMsg) {
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
