package com.example.widetech.data.models;

public class LoginResponse {
    private Response response;

    public LoginResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
