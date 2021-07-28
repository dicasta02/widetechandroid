package com.example.widetech.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.example.widetech.di.scope.ActivityContext;
import com.example.widetech.ui.base.BasePresenter;
import com.example.widetech.utilities.Constants;
import com.example.widetech.utilities.PreferenceManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class SplashPresenter extends BasePresenter<SplashView> {
    @Inject
    PreferenceManager preferenceManager;

    @Inject
    @ActivityContext
    Context context;

    private final long SPLASH_DELAY = TimeUnit.SECONDS.toMillis(2);

    @Inject
    public SplashPresenter() {
    }

    @Override
    public void attachView(SplashView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    void setDestiny(Intent requestIntent) {
        SystemClock.sleep(SPLASH_DELAY);

        if (preferenceManager.loadBool(Constants.KEY_REGISTERED, false)) {
            getView().showLogin();
        } else {
            getView().showLanding();
        }
    }
}
