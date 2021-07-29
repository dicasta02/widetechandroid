package com.example.widetech.ui.menu.contact;

import android.content.Context;

import com.example.widetech.data.models.Parameter;
import com.example.widetech.di.scope.ActivityContext;
import com.example.widetech.ui.base.BasePresenter;
import com.example.widetech.utilities.PreferenceManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


public class ContactPresenter extends BasePresenter<ContactView> {
    private CompositeDisposable disposable;

    @Inject
    @ActivityContext
    Context context;

    @Inject
    PreferenceManager preferenceManager;

    private List<Parameter> parameterList;

    @Inject
    public ContactPresenter() {

    }

    @Override
    public void attachView(ContactView view) {
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

}
