package mikaelquick.se.pizzastore.Utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat.checkSelfPermission

class MyLocation(val activity: FragmentActivity) {
    private val GPS_REQUEST = 100
    private var locationManager: LocationManager

    init {
        locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    fun getCurretLocation(): Location? {
        val passive = LocationManager.PASSIVE_PROVIDER
        val network = LocationManager.NETWORK_PROVIDER
        val gps = LocationManager.GPS_PROVIDER
        val isGPSActive =
            locationManager.isProviderEnabled(passive) || locationManager.isProviderEnabled(network) || locationManager.isProviderEnabled(
                gps
            )

        if (isGPSActive) {
            val fineLocationP = checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
            val coarseLocation =
                checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
            val noPermission = (fineLocationP != PackageManager.PERMISSION_GRANTED) && (coarseLocation != PackageManager.PERMISSION_GRANTED)
            if (noPermission) {
                requestPermissions(activity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                    GPS_REQUEST)
                throw Exception("No Permission")
            } else {
                locationManager.getLastKnownLocation(passive)?.let {
                    return it
                }
                locationManager.getLastKnownLocation(gps)?.let {
                    return it
                }
                locationManager.getLastKnownLocation(network)?.let {
                    return it
                }
            }
        }
        return null
    }
}