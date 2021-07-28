package com.example.widetech.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.example.widetech.ui.base.BaseActivity;
import com.example.widetech.ui.landing.LandingActivity;
import com.example.widetech.utilities.Constants;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashView {
    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);

        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashPresenter.detachView();
    }

    @Override
    public void showLanding() {
        callNextActivity(LandingActivity.class);
    }

    @Override
    public void showLogin() {

    }

    private void initUI() {
        splashPresenter.attachView(this);
        splashPresenter.setDestiny(getIntent());
    }

    private void callNextActivity(Class nextActivity) {
        Intent intent = new Intent(this, nextActivity);
        intent.putExtra(Constants.SHOW_SNACK_BAR, false);
        startActivity(intent);
        finish();
    }
}