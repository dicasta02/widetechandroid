package com.example.widetech.helpers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import com.example.widetech.utilities.LogManager;

public class PermissionHelper {
    public static boolean isPermissionGrantedForUsed(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void printPermissionStatus(Context context) {
        new LogManager(PermissionHelper.class).printDebug("ACCESS_COARSE_LOCATION is granted ->",
                isPermissionGrantedForUsed(context, Manifest.permission.ACCESS_COARSE_LOCATION));
        new LogManager(PermissionHelper.class).printDebug("ACCESS_FINE_LOCATION is granted ->",
                isPermissionGrantedForUsed(context, Manifest.permission.ACCESS_FINE_LOCATION));
    }
}
