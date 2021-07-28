package com.example.widetech.ui.base;

import android.content.DialogInterface;

import com.example.widetech.di.component.ActivityComponent;


/**
 * @author dacastano
 * @version 1.0
 * @since 07/12/2017
 */
public interface BaseView {
    ActivityComponent getActivityComponent();

    void showDialog(String title, String msg);

    void showDialog(String title, String msg, String positiveTitle, DialogInterface.OnClickListener positiveButton);

    void showDialogNoInternet();

    void showCustomProgress(String message);

    void hideCustomProgress();
}
