package com.example.widetech.utilities;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AppLocationManager {
    private final Context context;
    private LocationManager locationManager;

    public AppLocationManager(Context context) {
        this.context = context;
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public LatLng getCurrentLocation(LocationListener locationListener) {
        LatLng currentLocation = new LatLng(0, 0);
        Location location;

//        GPS gives more accurate data
        if (this.isProviderGPS()) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {
                return new LatLng(location.getLatitude(), location.getLongitude());
            }
        }

        if (this.isProviderNetwork()) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                return new LatLng(location.getLatitude(), location.getLongitude());
            }
        }

        return currentLocation;
    }

    public Address getAddress(LatLng location) {
        Geocoder geocoder = new Geocoder(this.context, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);

            if (addresses != null && !addresses.isEmpty()) {
                return addresses.get(0);
            }
        } catch (IOException e) {
            new LogManager(AppLocationManager.class).printError(e);
        }

        return null;
    }

    public boolean isProviderGPS() {
        return this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public boolean isProviderNetwork() {
        return this.locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
