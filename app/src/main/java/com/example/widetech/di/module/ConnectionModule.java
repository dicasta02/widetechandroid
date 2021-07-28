package com.example.widetech.di.module;

import com.example.widetech.data.network.RetrofitService;
import com.example.widetech.di.scope.CallContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ConnectionModule {
    @Provides
    @Singleton
    RetrofitService provideRetrofitService() {
        return RetrofitService.Creator.newRetrofitServiceAdapter();
    }

}
