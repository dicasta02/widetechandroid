package com.example.widetech.ui.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.widetech.R;
import com.example.widetech.ui.base.BaseActivity;
import com.example.widetech.utilities.FontHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends BaseActivity implements MenuView, OnFragmentInteractionListener {
    @BindView(R.id.menuNavView)
    BottomNavigationView menuNavView;
    @BindView(R.id.globalConstraint)
    ConstraintLayout globalConstraint;

    @Inject
    MenuPresenter presenter;
    @Inject
    FontHelper fontHelper;

    private OnRequestPermissionListener onRequestPermissionListener;

    public interface OnRequestPermissionListener {
        void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getActivityComponent().inject(this);
        ButterKnife.bind(this);

        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.detachView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        onRequestPermissionListener.onRequestPermissionsResult(requestCode, grantResults);
    }

    @Override
    public void setMenuOption(int itemId) {
        menuNavView.setSelectedItemId(itemId);
    }

    @Override
    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.menuContainer, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showSnackBar(boolean showSnackBar) {
        if (showSnackBar) {
            Snackbar snackbar = Snackbar.make(globalConstraint, R.string.msgRegister, Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
            snackbar.show();
        }
    }

    @Override
    public void setRequestPermissionsListener(MenuActivity.OnRequestPermissionListener listener) {
        this.onRequestPermissionListener = listener;
    }

    private void initUI() {
        presenter.attachView(this);
        fontHelper.applyFont(findViewById(R.id.globalConstraint));

        menuNavView.setOnNavigationItemSelectedListener(menuItem -> {
            presenter.handleNavigationItemSelected(menuItem.getItemId());

            return true;
        });

        presenter.getDataIntent(getIntent());
        presenter.prepareContent(getIntent());
    }
}
