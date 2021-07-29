package com.example.widetech.ui.landing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.widetech.R;
import com.example.widetech.ui.base.BaseActivity;
import com.example.widetech.ui.construction.ConstructionActivity;
import com.example.widetech.ui.login.LoginActivity;
import com.example.widetech.ui.menu.MenuActivity;
import com.example.widetech.utilities.FontHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LandingActivity extends BaseActivity implements LandingView {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txtVersion)
    TextView txtVersion;

    @Inject
    LandingPresenter landingPresenter;

    @Inject
    FontHelper fontHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_page);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);

        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        landingPresenter.detachView();
    }

    @Override
    public void setVersion(String version) {
        landingPresenter.setVersion(version);
        txtVersion.setText(version);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnLogin)
    public void onClickLogin() {
        callNextActivity(LoginActivity.class);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnCreateAccount)
    public void OnClickCreateAccount() {
        callNextActivity(MenuActivity.class);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.imgButtomFacebook)
    public void OnClickLoginFacebook() {
        callNextActivity(ConstructionActivity.class);
    }

    private void initUI() {
        landingPresenter.attachView(this);
        fontHelper.applyFont(findViewById(R.id.globalConstraint));
    }

    private void callNextActivity(Class nextActivity) {
        Intent intent = new Intent(this, nextActivity);
        startActivity(intent);
        finish();
    }

}