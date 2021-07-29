package com.example.widetech.data.dataManager;

import com.example.widetech.data.models.GetProductsResponse;
import com.example.widetech.data.models.LoginResponse;
import com.example.widetech.data.network.RetrofitService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RepositoryDataManager {
    private final RetrofitService retrofitService;

    @Inject
    RetrofitService callRetrofitService;

    @Inject
    public RepositoryDataManager(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    public Observable<LoginResponse> doLogin(String email, String Password) {
        return retrofitService.doLogin(email, Password);
    }

    public Observable<GetProductsResponse> getInfoProducts(String responseType) {
        return retrofitService.getInfoProducts(responseType);
    }

}
