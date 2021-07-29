package com.example.widetech.ui.menu.more;

import android.content.Context;
import android.content.res.TypedArray;

import com.example.widetech.R;
import com.example.widetech.data.dataManager.RepositoryDataManager;
import com.example.widetech.data.models.MenuItem;
import com.example.widetech.data.models.Parameter;
import com.example.widetech.data.models.PrepaidProducts;
import com.example.widetech.di.scope.ActivityContext;
import com.example.widetech.helpers.PermissionHelper;
import com.example.widetech.ui.adapter.MenuAdapter;
import com.example.widetech.ui.base.BasePresenter;
import com.example.widetech.utilities.Constants;
import com.example.widetech.utilities.LogManager;
import com.example.widetech.utilities.NetworkUtils;
import com.example.widetech.utilities.PreferenceManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MoreOptionsPresenter extends BasePresenter<MoreOptionsView> {
    private CompositeDisposable disposable;
    private RepositoryDataManager repositoryDataManager;

    @Inject
    @ActivityContext
    Context context;

    @Inject
    PreferenceManager preferenceManager;

    private List<Parameter> parameterList;

    @Inject
    public MoreOptionsPresenter(RepositoryDataManager repositoryDataManager) {
        this.repositoryDataManager = repositoryDataManager;
    }

    @Override
    public void attachView(MoreOptionsView view) {
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

    public void loadContent() {
        MenuAdapter menuAdapter = new MenuAdapter(getData());
        getView().loadAdapter(menuAdapter);
        PermissionHelper.printPermissionStatus(context);
    }

    public void handleOption(MenuItem optionSelected) {
        switch (optionSelected.getIndex()) {
            case Constants.PRODUCT_ONE:

                break;
            case Constants.PRODUCT_TWO:

                break;
            case Constants.PRODUCT_TREE:

                break;
            case Constants.PRODUCT_FOUR:

                break;
            case Constants.PRODUCT_FIVE:

                break;
        }
    }

    public void getInfoAccount() {
        if (NetworkUtils.isNetworkConnected(context)) {
            new LogManager(MoreOptionsPresenter.class).printDebug("Get account information");
            getView().showCustomProgress(context.getString(R.string.dialogWait));

            disposable.add(repositoryDataManager.getInfoProducts(Constants.RESPONSE_TYPE)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(data -> {
                        new LogManager(MoreOptionsPresenter.class).printDebug("response OK:", data.toString());

                        if (data.getProductResponse().getRecords().getListRecord() != null && !data.getProductResponse().getRecords().getListRecord().isEmpty()) {
                            PrepaidProducts accountPlanInfo = new Gson().fromJson(data.getProductResponse().getRecords().getListRecord().get(0).get(Constants.ACCOUNT_PLAN_INFO)
                                    .toString(), PrepaidProducts.class);

                            getView().loadInfoAccount(accountPlanInfo.getResponseCode());
                        }

                        getView().hideCustomProgress();
                    }, throwable -> {
                        new LogManager(MoreOptionsPresenter.class).printDebug("throwable cause:", throwable.getCause());
                        new LogManager(MoreOptionsPresenter.class).printDebug("throwable message:", throwable.getMessage());
                        getView().hideCustomProgress();
                        getView().hideFieldAccount();
                    }));
        } else {
            getView().hideFieldAccount();
        }
    }


    private List<MenuItem> getData() {
        List<MenuItem> menus = new ArrayList<>();

        TypedArray icons = context.getResources().obtainTypedArray(R.array.menuIcons);
        String[] textName = context.getResources().getStringArray(R.array.menuList);

        for (int i = 0; i < textName.length; i++) {
            MenuItem item = new MenuItem();
            item.setIndex((byte) i);
            item.setImgIcon(icons.getResourceId(i, -1));
            item.setTxtName(textName[i]);
            menus.add(item);
        }

        icons.recycle();

        return menus;
    }
}
