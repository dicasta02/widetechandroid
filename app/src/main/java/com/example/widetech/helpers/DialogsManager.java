package com.example.widetech.helpers;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.widetech.R;
import com.example.widetech.utilities.Constants;

public class DialogsManager extends DialogFragment {
    public final static int PROGRESS = 0;
    public final static int INFORMATION = 1;
    public final static int INFORMATION_1_BUTTON = 2;

    public int currentDialog = -1;

    private AlertDialog.Builder dialog;
    private Bundle args;

    private String acceptTitle;
    private DialogInterface.OnClickListener acceptListener;

    public static DialogsManager newInstance(int type, String title, String message) {
        DialogsManager itmDialogs = new DialogsManager();
        Bundle args = new Bundle();
        args.putInt(Constants.TYPE, type);
        args.putString(Constants.TITLE, title);
        args.putString(Constants.MSG, message);
        itmDialogs.setArguments(args);

        return itmDialogs;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        args = getArguments();
        setCancelable(false);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppDialogStyle);

        currentDialog = args.getInt(Constants.TYPE);
        switch (args.getInt(Constants.TYPE)) {
            case PROGRESS:
                return buildProgress();
            case INFORMATION:
                buildInformation();
                break;
            case INFORMATION_1_BUTTON:
                buildInformationOneButton();
                break;
            default:
                buildInformation();
                break;
        }

        return dialog.create();
    }

    public void setAcceptListener(String acceptTitle, DialogInterface.OnClickListener acceptListener) {
        this.acceptTitle = acceptTitle;
        this.acceptListener = acceptListener;
    }

    private void buildInformation() {
        dialog = new AlertDialog.Builder(getActivity(), R.style.AppDialogStyle);
        dialog.setTitle(args.getString(Constants.TITLE));
        dialog.setMessage(args.getString(Constants.MSG));
        dialog.setPositiveButton(getString(R.string.allAccept), (dialog, which) -> dismiss());
    }

    private ProgressDialog buildProgress() {
        ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.AlertDialog_Theme);

        progressDialog.setMessage(args.getString(Constants.MSG));
        progressDialog.setIndeterminate(true);

        return progressDialog;
    }

    private void buildInformationOneButton() {
        dialog = new AlertDialog.Builder(getActivity(), R.style.AppDialogStyle);
        dialog.setTitle(args.getString(Constants.TITLE));
        dialog.setMessage(args.getString(Constants.MSG));

        if (acceptListener != null) {
            dialog.setPositiveButton(acceptTitle, acceptListener);
        } else {
            dialog.setPositiveButton(getString(R.string.allAccept), (dialog, which) -> dismiss());
        }
    }
}
