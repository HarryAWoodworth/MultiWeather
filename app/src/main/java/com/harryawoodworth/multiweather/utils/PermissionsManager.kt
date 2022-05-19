package com.harryawoodworth.multiweather.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Checks for and requests permissions from the user.
 * Follows the Singleton pattern using 'object'
 */
object PermissionsManager {

    // Permission request codes
    const val COARSE_LOCATION_CODE = 100
    const val FINE_LOCATION_CODE = 101

    /**
     * Match permission with request code and then check for permission. If permission is not granted, request it.
     * Return whether or not the permission has been granted.
     */
    fun checkPermission(activityContext: Activity, permission: String) {
        when(permission) {
            Manifest.permission.ACCESS_COARSE_LOCATION -> checkPermissionWithRequestCode(activityContext, permission, COARSE_LOCATION_CODE)
            Manifest.permission.ACCESS_FINE_LOCATION -> checkPermissionWithRequestCode(activityContext, permission, FINE_LOCATION_CODE)
        }
    }

    /**
     * Check or request for location permissions. Return whether or not they were granted
     */
    fun hasLocationPermissions(activityContext: Activity): Boolean {
        return isPermissionGranted(activityContext, Manifest.permission.ACCESS_COARSE_LOCATION) ||
                isPermissionGranted(activityContext, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    /**
     * Check if a permission is granted. If not, request it.
     */
    private fun checkPermissionWithRequestCode(activityContext: Activity, permission: String, requestCode: Int) {
        if(ContextCompat.checkSelfPermission(activityContext, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activityContext, arrayOf(permission), requestCode)
        }
    }

    /**
     * Return whether or not a permission is granted.
     */
    fun isPermissionGranted(activityContext: Activity, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(activityContext, permission) == PackageManager.PERMISSION_GRANTED
    }

}