package com.example.widetech.utilities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionsManager {
    public static final int REQUEST_LOCATION = 0x01;

    private final Context context;

    public PermissionsManager(Context context) {
        this.context = context;
    }

    public boolean isPermissionGranted(Permission permission) {
        return ContextCompat.checkSelfPermission(context, permission.permissionTag) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Request a permission.
     *
     * @param activity    requesting the permission
     * @param requestCode to listen for in {@link Activity#onRequestPermissionsResult(int, String[], int[])}
     * @param permissions to request
     */
    public void requestPermission( Activity activity, int requestCode,  Permission... permissions) {
        String[] permissionsList = new String[permissions.length];
        int index = 0;

        for (Permission permission : permissions) {
            permissionsList[index] = permission.permissionTag;
            index++;
        }

        ActivityCompat.requestPermissions(activity, permissionsList, requestCode);
    }

    public enum Permission {
        ACCESS_COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION),
        ACCESS_FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION);
        private final String permissionTag;

        Permission(String permissionTag) {
            this.permissionTag = permissionTag;
        }
    }
}