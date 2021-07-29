package com.example.widetech.ui.maps;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.example.widetech.R;
import com.example.widetech.ui.base.BasePresenter;
import com.example.widetech.utilities.AppLocationManager;
import com.example.widetech.utilities.PermissionsManager;
import com.example.widetech.utilities.StringUtils;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

public class MapsPresenter extends BasePresenter<MapsView> {
    @Inject
    Activity activity;
    @Inject
    PermissionsManager permissionsManager;
    @Inject
    AppLocationManager appLocationManager;

    @Inject
    public MapsPresenter() {
    }

    @Override
    public void attachView(MapsView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void prepareContent(FragmentManager fragmentManager) {
        this.loadMap(fragmentManager);
    }

    public void doUserLocationAction() {
        if (this.permissionsManager.isPermissionGranted(PermissionsManager.Permission.ACCESS_COARSE_LOCATION)
                && this.permissionsManager.isPermissionGranted(PermissionsManager.Permission.ACCESS_FINE_LOCATION)) {
            this.getUserLocation();
        } else {
            requestLocationPermission();
        }
    }

    public void onRequestPermissionsResult(int requestCode, int[] grantResults, boolean showRationale) {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && requestCode == PermissionsManager.REQUEST_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            this.getUserLocation();
        } else {
            if (!showRationale) {
                if (requestCode == PermissionsManager.REQUEST_LOCATION) {
                    getView().showDialog(activity.getString(R.string.app_name),
                            activity.getString(R.string.allNeverPermission, activity.getString(R.string.allNeverPermissionLocation)));
                }
            } else {
                getView().showDialog(activity.getString(R.string.app_name),
                        activity.getString(R.string.allRequiredPermission, activity.getString(R.string.allNeverPermissionLocation)));
            }
        }
    }

    public void handleSelectedLocation(LatLng location) {
        getView().clearMap();
        this.setUserLocation(location);
    }

    private void loadMap(FragmentManager fragmentManager) {
        SupportMapFragment fragmentMap = (SupportMapFragment) fragmentManager.findFragmentById(R.id.fragmentMap);

        if (fragmentMap != null) {
            getView().loadMap(fragmentMap);
        }
    }

    private void requestLocationPermission() {
        this.permissionsManager.requestPermission(activity, PermissionsManager.REQUEST_LOCATION, PermissionsManager.Permission.ACCESS_COARSE_LOCATION,
                PermissionsManager.Permission.ACCESS_FINE_LOCATION);
    }

    private void getUserLocation() {
        if (this.appLocationManager.isProviderGPS() || this.appLocationManager.isProviderNetwork()) {
            LatLng userLocation = this.appLocationManager.getCurrentLocation(new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    // Unnecessary
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    // Unnecessary
                }

                @Override
                public void onProviderEnabled(String provider) {
                    // Unnecessary
                }

                @Override
                public void onProviderDisabled(String provider) {
                    // Unnecessary
                }
            });

            if (userLocation.latitude != 0 && userLocation.longitude != 0) {
                this.setUserLocation(userLocation);
            }
        }
    }

    private void setUserLocation(LatLng location) {
        Address address = this.appLocationManager.getAddress(location);

        if (address != null && !StringUtils.isEmpty(address.getThoroughfare())) {
            getView().setLocation(location, address.getThoroughfare());
        } else {
            getView().setLocation(location, "");
        }
    }
}
