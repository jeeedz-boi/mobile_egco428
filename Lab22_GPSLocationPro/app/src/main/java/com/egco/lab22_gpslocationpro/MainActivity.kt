package com.egco.lab22_gpslocationpro

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var locationManger: LocationManager
    private lateinit var locationListener: LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationManger = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object: LocationListener{
            override fun onLocationChanged(location: Location) {
                textView.append("\n"+location.latitude+" , "+location.longitude)
            }
            override fun onProviderDisabled(provider: String) {
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }
        requestLocationButton()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            10 -> requestLocationButton()
            else -> { }
        }
    }

    private fun requestLocationButton() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                requestPermissions  (arrayOf(
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.INTERNET),10
                                    )
            }
            return
        }
        locationReqBtn.setOnClickListener{
            locationManger.requestLocationUpdates("gps",5000,0f,locationListener)
        }
    }

    override fun onPause() {
        super.onPause()
        locationManger.removeUpdates(locationListener)
        Log.d("GPSStatus", "On Pause!")
    }
}

//AIzaSyDnAzSPxFtuF8PXuGLOa0s7sdEKsskqa6o