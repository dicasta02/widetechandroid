package com.example.widetech.ui.menu.more;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.widetech.R;
import com.example.widetech.data.models.MenuItem;
import com.example.widetech.helpers.RecyclerDividerItemDecoration;
import com.example.widetech.ui.adapter.GeneralAdapterView;
import com.example.widetech.ui.adapter.MenuAdapter;
import com.example.widetech.ui.base.BaseFragment;
import com.example.widetech.utilities.FontHelper;
import com.example.widetech.utilities.PreferenceManager;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author dacastano
 * @version 1.0
 * @since 11/12/2017
 */
public class MoreOptionsFragment extends BaseFragment implements MoreOptionsView, GeneralAdapterView<MenuItem> {
    @BindView(R.id.globalConstraint)
    ConstraintLayout globalConstraint;
    @BindView(R.id.recyclerViewMenu)
    RecyclerView recyclerViewMenu;

    @Inject
    MoreOptionsPresenter moreOptionsPresenter;

    @Inject
    PreferenceManager preferenceManager;

    @Inject
    FontHelper fontHelper;

    Context context;

    public static MoreOptionsFragment newInstance() {
        return new MoreOptionsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        getActivityComponent().inject(this);
        ButterKnife.bind(this, view);

        initUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        setDataForInfoAccount();
    }

    @Override
    public void nextActivity(Class nextActivity) {
        Intent intent = new Intent(getContext(), nextActivity);
        startActivity(intent);
    }

    @Override
    public void hideFieldAccount() {

    }


    @Override
    public void loadAdapter(MenuAdapter menuAdapter) {
        menuAdapter.setAdapterView(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMenu.setLayoutManager(layoutManager);
        recyclerViewMenu.setHasFixedSize(true);
        recyclerViewMenu.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMenu.addItemDecoration(new RecyclerDividerItemDecoration(Objects.requireNonNull(getContext()),
                RecyclerDividerItemDecoration.VERTICAL_LIST));
        recyclerViewMenu.setAdapter(menuAdapter);
    }

    @Override
    public void loadInfoAccount(int product) {

    }


    @Override
    public void onItemSelected(MenuItem itemSelected) {
        moreOptionsPresenter.handleOption(itemSelected);
    }

    private void initUI() {
        moreOptionsPresenter.attachView(this);
        moreOptionsPresenter.loadContent();

        fontHelper.applyFont(globalConstraint);
    }

    private void setDataForInfoAccount() {
        moreOptionsPresenter.getInfoAccount();
    }
}
