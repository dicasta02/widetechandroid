package com.example.widetech.ui.base;

import android.app.ProgressDialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.util.Log;

import com.example.widetech.CustomApp;
import com.example.widetech.R;
import com.example.widetech.di.component.ActivityComponent;
import com.example.widetech.di.component.ApplicationComponent;
import com.example.widetech.di.module.ActivityModule;
import com.example.widetech.helpers.DialogsManager;
import com.example.widetech.utilities.Constants;
import com.example.widetech.utilities.LogManager;


/**
 * @author dacastano
 * @version 1.0
 * @since 14/12/2017
 */
public abstract class BaseFragment extends Fragment implements BaseView {
    private ActivityComponent activityComponent;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent applicationComponent = ((CustomApp) getActivity().getApplicationContext())
                .getApplicationComponent();
        this.activityComponent = applicationComponent.addActivityComponent(new ActivityModule(getActivity()));

        progressDialog = new ProgressDialog(getActivity(), R.style.AlertDialog_Theme);
        progressDialog.setCancelable(false);
    }

    @Override
    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    public void showDialog(String title, String msg) {
        showDialog(DialogsManager.newInstance(DialogsManager.INFORMATION, title, msg));
    }

    @Override
    public void showDialog(String title, String msg, String positiveTitle, OnClickListener positiveButton) {
        DialogsManager dialogsManager = DialogsManager.newInstance(DialogsManager.INFORMATION_1_BUTTON,
                title, msg);
        dialogsManager.setAcceptListener(positiveTitle, positiveButton);
        showDialog(dialogsManager);
    }

    @Override
    public void showDialogNoInternet() {
        showDialog(DialogsManager.newInstance(DialogsManager.INFORMATION, getString(R.string.app_name),
                getString(R.string.allNoInternet)));
    }

    @Override
    public void showCustomProgress(String message) {
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    @Override
    public void hideCustomProgress() {
        try {
            progressDialog.dismiss();
        } catch (Exception e) {
            new LogManager(BaseActivity.class).printError(e);
        }
    }

    private void showDialog(DialogsManager dialogsManager) {
        dismissDialog();

        FragmentManager fm = getFragmentManager();

        if (fm != null) {
            fm.beginTransaction().add(dialogsManager, Constants.DIALOG).commitAllowingStateLoss();
        }
    }

    private void dismissDialog() {
        try {
            if (getFragmentManager() != null) {
                getFragmentManager().executePendingTransactions();
                Fragment prev = getFragmentManager().findFragmentByTag(Constants.DIALOG);

                if (prev != null) {
                    DialogsManager df = (DialogsManager) prev;
                    df.dismissAllowingStateLoss();
                }
            }
        } catch (Exception e) {
            Log.e(Constants.REFERENCE_ID, "Error in dismiss dialog -> " + e.getMessage());
        }
    }
}
