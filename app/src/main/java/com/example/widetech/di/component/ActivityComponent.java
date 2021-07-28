package com.example.widetech.di.component;

import com.example.widetech.di.module.ActivityModule;
import com.example.widetech.ui.construction.ConstructionActivity;
import com.example.widetech.ui.landing.LandingActivity;
import com.example.widetech.ui.splash.SplashActivity;

import dagger.Subcomponent;

@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity splashActivity);

    void inject(LandingActivity landingActivity);

    void inject(ConstructionActivity constructionActivity);

}
