package com.example.widetech.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.widetech.R;
import com.example.widetech.ui.base.BaseActivity;
import com.example.widetech.ui.menu.MenuActivity;
import com.example.widetech.utilities.Constants;
import com.example.widetech.utilities.FontHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {
    @BindView(R.id.edtUser)
    EditText edtUser;
    @BindView(R.id.edtPassword)
    EditText edtPassword;

    @Inject
    LoginPresenter loginPresenter;

    @Inject
    FontHelper fontHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActivityComponent().inject(this);
        ButterKnife.bind(this);

        initUI();
    }

    @Override
    public void setStringMessage(int message) {
        showDialog(getString(R.string.app_name), getString(message));
    }

    @Override
    public void showNextActivity(boolean showSnackBar) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(Constants.SHOW_SNACK_BAR, showSnackBar);
        startActivity(intent);
        finish();
    }

    @Override
    public void clearFields() {
        edtUser.setText("");
        edtPassword.setText("");
    }

    @OnClick(R.id.btnLogin)
    public void onClickLogin() {
        loginPresenter.validateData(edtUser.getText().toString(), edtPassword.getText().toString());
    }

    private void initUI() {
        loginPresenter.attachView(this);
        fontHelper.applyFont(findViewById(R.id.globalConstraint));
    }

}