package com.example.widetech.di.component;

import com.example.widetech.di.module.ActivityModule;
import com.example.widetech.ui.construction.ConstructionActivity;
import com.example.widetech.ui.landing.LandingActivity;
import com.example.widetech.ui.login.LoginActivity;
import com.example.widetech.ui.maps.MapsActivity;
import com.example.widetech.ui.menu.MenuActivity;
import com.example.widetech.ui.menu.contact.ContactFragment;
import com.example.widetech.ui.menu.more.MoreOptionsFragment;
import com.example.widetech.ui.splash.SplashActivity;

import dagger.Subcomponent;

@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity splashActivity);

    void inject(LandingActivity landingActivity);

    void inject(ConstructionActivity constructionActivity);

    void inject(LoginActivity loginActivity);

    void inject(MenuActivity menuActivity);

    void inject(MapsActivity mapsActivity);

    void inject(MoreOptionsFragment moreOptionsFragment);

    void inject(ContactFragment contactFragment);

}
