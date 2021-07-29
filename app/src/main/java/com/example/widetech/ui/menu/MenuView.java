package com.example.widetech.ui.menu;

import androidx.fragment.app.Fragment;

import com.example.widetech.ui.base.BaseView;

public interface MenuView extends BaseView {
    void setMenuOption(int itemId);

    void setFragment(Fragment fragment);

    void showSnackBar(boolean showSnackBar);
}
