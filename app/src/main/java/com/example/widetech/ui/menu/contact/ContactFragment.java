package com.example.widetech.ui.menu.contact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.widetech.R;
import com.example.widetech.ui.base.BaseFragment;
import com.example.widetech.ui.maps.MapsActivity;
import com.example.widetech.utilities.FontHelper;
import com.example.widetech.utilities.PreferenceManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author dacastano
 * @version 1.0
 * @since 11/12/2017
 */
public class ContactFragment extends BaseFragment implements ContactView {
    @BindView(R.id.globalConstraint)
    ConstraintLayout globalConstraint;

    @Inject
    ContactPresenter moreOptionsPresenter;

    @Inject
    PreferenceManager preferenceManager;

    @Inject
    FontHelper fontHelper;

    Context context;

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        getActivityComponent().inject(this);
        ButterKnife.bind(this, view);

        initUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @OnClick(R.id.btnGotoMap)
    public void onClickGoToMap() {
        Intent intent = new Intent(getActivity().getApplication(), MapsActivity.class);
        startActivity(intent);
    }

    private void initUI() {
        moreOptionsPresenter.attachView(this);
        fontHelper.applyFont(globalConstraint);
    }

}
