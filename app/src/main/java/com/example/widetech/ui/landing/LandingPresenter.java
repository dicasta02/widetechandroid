package com.example.widetech.ui.landing;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.widetech.di.scope.ActivityContext;
import com.example.widetech.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


public class LandingPresenter extends BasePresenter<LandingView> {
    private CompositeDisposable disposable;

    @Inject
    Activity activity;

    @Inject
    @ActivityContext
    Context context;

    @Inject
    LandingPresenter() {

    }

    @Override
    public void attachView(LandingView view) {
        super.attachView(view);

        if (disposable == null) {
            disposable = new CompositeDisposable();
        }
    }

    @Override
    public void detachView() {
        super.detachView();

        if (disposable != null && !disposable.isDisposed()) {
            disposable.clear();
            disposable.dispose();
        }
    }

    public void setVersion(String version) {
        if (version != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                version = packageInfo.versionName;
                getView().setVersion(version);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
