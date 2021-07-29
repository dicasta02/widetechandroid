package com.example.widetech.ui.login;

import android.content.Context;

import com.example.widetech.R;
import com.example.widetech.data.dataManager.RepositoryDataManager;
import com.example.widetech.di.scope.ActivityContext;
import com.example.widetech.ui.base.BasePresenter;
import com.example.widetech.utilities.Constants;
import com.example.widetech.utilities.LogManager;
import com.example.widetech.utilities.NetworkUtils;
import com.example.widetech.utilities.PreferenceManager;
import com.example.widetech.utilities.StringUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginView> {
    private CompositeDisposable disposable;
    private RepositoryDataManager repositoryDataManager;

    @Inject
    @ActivityContext
    Context context;

    @Inject
    PreferenceManager preferenceManager;

    @Inject
    public LoginPresenter(RepositoryDataManager repositoryDataManager) {
        this.repositoryDataManager = repositoryDataManager;
    }

    @Override
    public void attachView(LoginView view) {
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

    public void validateData(String email, String password) {
        if (StringUtils.isEmpty(email)) {
            getView().setStringMessage(R.string.msgEmail);
            return;
        }

        if (StringUtils.isEmpty(password)) {
            getView().setStringMessage(R.string.msgPassword);
            return;
        }


        doLogin(email, password);
    }

    public void doLogin(String email, String password) {
        if (NetworkUtils.isNetworkConnected(context)) {
            getView().showCustomProgress(context.getString(R.string.dialogWait));
            disposable.add(repositoryDataManager.doLogin(email, password)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(data -> {
                        new LogManager(LoginPresenter.class).printDebug("Response OK:", data.toString());

                        if (data.getResponse().getResponseCode() == Constants.OK) {
                            preferenceManager.saveBool(Constants.KEY_REGISTERED, true);
                            getView().showNextActivity(true);
                        } else {
                            getView().showDialog(context.getString(R.string.app_name),
                                    context.getString(R.string.msgUserNotExist));
                            getView().hideCustomProgress();
                            getView().clearFields();
                        }
                    }, throwable -> {
                        getView().showDialog(context.getString(R.string.app_name),
                                context.getString(R.string.msgErrorServer));
                        getView().clearFields();
                        getView().hideCustomProgress();
                    }));
        } else {
            getView().showDialogNoInternet();
            getView().clearFields();
        }

    }
}
