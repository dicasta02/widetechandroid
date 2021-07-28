package com.example.widetech.di.module;

import android.app.Activity;
import android.content.Context;

import com.example.widetech.di.scope.ActivityContext;
import com.example.widetech.utilities.FontHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    Activity provideActivity(){
        return activity;
    }

    @Provides
    @ActivityContext
    Context provideContext(){
        return activity;
    }

    @Provides
    FontHelper provideFont(){
        return new FontHelper(activity);
    }
}
