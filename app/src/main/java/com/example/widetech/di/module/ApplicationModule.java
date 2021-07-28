package com.example.widetech.di.module;

import android.app.Application;
import android.content.Context;

import com.example.widetech.di.scope.ApplicationContext;
import com.example.widetech.utilities.PreferenceManager;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    protected final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    PreferenceManager preferenceManager() {
        return new PreferenceManager(application.getBaseContext());
    }

    @Provides
    Gson provideGson() {
        return new Gson();
    }
}
