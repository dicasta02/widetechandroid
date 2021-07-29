package com.example.widetech.ui.menu;

import android.content.Context;
import android.content.Intent;

import com.example.widetech.di.scope.ActivityContext;
import com.example.widetech.ui.base.BasePresenter;
import com.example.widetech.ui.menu.contact.ContactFragment;
import com.example.widetech.ui.menu.more.MoreOptionsFragment;
import com.example.widetech.utilities.Constants;

import javax.inject.Inject;

public class MenuPresenter extends BasePresenter<MenuView>  {
    @Inject
    @ActivityContext
    Context context;

    @Inject
    MenuPresenter() {

    }

    public void prepareContent(Intent intent) {
        if (intent.getExtras() != null) {
            getView().setMenuOption(intent.getExtras().getInt(Constants.MENU_OPTION,
                    Constants.MENU_ITEM_MORE));
        } else {
            getView().setMenuOption(Constants.MENU_ITEM_MORE);
        }
    }

    void handleNavigationItemSelected(int itemId) {
        switch (itemId) {
            case Constants.MENU_ITEM_CONTACT:
                getView().setFragment(ContactFragment.newInstance());
                break;
            case Constants.MENU_ITEM_MORE:
                getView().setFragment(MoreOptionsFragment.newInstance());
                break;
        }
    }

    public void getDataIntent(Intent intent) {
        boolean showSnackBar = intent.getBooleanExtra(Constants.SHOW_SNACK_BAR, false);
        getView().showSnackBar(showSnackBar);
    }
}

