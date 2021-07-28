package com.example.widetech.data.dataManager;

import com.example.widetech.data.network.RetrofitService;
import com.example.widetech.di.scope.CallContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryDataManager {
    private RetrofitService retrofitService;

    @Inject
    @CallContext
    RetrofitService callRetrofitService;

    @Inject
    public RepositoryDataManager(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

}
