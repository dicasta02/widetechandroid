package com.example.widetech.ui.maps;


import android.os.Bundle;

import com.example.widetech.R;
import com.example.widetech.ui.base.BaseActivity;
import com.example.widetech.utilities.LogManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import butterknife.OnClick;

public class MapsActivity extends BaseActivity implements MapsView, OnMapReadyCallback {
    private GoogleMap googleMap;
    private LatLng currentLocation;

    @Inject
    MapsPresenter mapsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getActivityComponent().inject(this);

        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapsPresenter.detachView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mapsPresenter.onRequestPermissionsResult(requestCode, grantResults, shouldShowRequestPermissionRationale(permissions[0]));
    }

    @Override
    public void loadMap(SupportMapFragment fragmentMap) {
        try {
            fragmentMap.getMapAsync(this);
        } catch (Exception e) {
            new LogManager(MapsActivity.class).printError(e);
        }
    }

    @Override
    public void setLocation(LatLng location, String title) {
        this.currentLocation = location;
        this.googleMap.addMarker(new MarkerOptions().position(location).title(title).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_maker)));
        this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom((location), 17));
    }

    @Override
    public void clearMap() {
        this.googleMap.clear();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        this.mapsPresenter.doUserLocationAction();
        this.googleMap.getUiSettings().setZoomControlsEnabled(true);
        this.googleMap.getUiSettings().setCompassEnabled(true);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        this.googleMap.setOnMapClickListener(latLng -> this.mapsPresenter.handleSelectedLocation(latLng));
    }

    @OnClick(R.id.btnBack)
    public void onCLickBack() {
        finish();
    }

    private void initUI() {
        mapsPresenter.attachView(this);
        this.currentLocation = new LatLng(0, 0);
        this.mapsPresenter.prepareContent(getSupportFragmentManager());
    }


}