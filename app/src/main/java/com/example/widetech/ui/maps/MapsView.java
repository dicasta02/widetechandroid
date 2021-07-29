package com.example.widetech.ui.maps;

import com.example.widetech.ui.base.BaseView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public interface MapsView extends BaseView {
    void loadMap(SupportMapFragment fragmentMap);

    void setLocation(LatLng location, String title);

    void clearMap();
}
