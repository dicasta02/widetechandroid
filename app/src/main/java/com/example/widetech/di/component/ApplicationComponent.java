package com.example.widetech.di.component;

import android.content.Context;

import com.example.widetech.di.module.ActivityModule;
import com.example.widetech.di.module.ApplicationModule;
import com.example.widetech.di.module.ConnectionModule;
import com.example.widetech.di.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ConnectionModule.class})
public interface ApplicationComponent {
    @ApplicationContext
    Context context();

    ActivityComponent addActivityComponent(ActivityModule activityModule);
}
