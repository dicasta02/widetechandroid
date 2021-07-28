package com.example.widetech.ui.construction;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.widetech.R;
import com.example.widetech.ui.base.BaseActivity;
import com.example.widetech.utilities.FontHelper;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConstructionActivity extends BaseActivity implements ConstructionView {

    @Inject
    ConstructionPresenter constructionPresenter;

    @Inject
    FontHelper fontHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);

        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        constructionPresenter.detachView();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnBack)
    public void onCLickBack(){
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);

    }


    private void initUI() {
        constructionPresenter.attachView(this);
        fontHelper.applyFont(findViewById(R.id.globalConstraint));
    }

}