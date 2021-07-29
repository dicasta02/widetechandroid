package com.example.widetech.ui.login;

import com.example.widetech.ui.base.BaseView;

public interface LoginView extends BaseView {
    void setStringMessage(int message);

    void showNextActivity(boolean showSnackBar);

    void clearFields();
}
