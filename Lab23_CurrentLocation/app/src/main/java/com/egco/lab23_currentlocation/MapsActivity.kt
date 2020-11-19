package com.egco.lab23_currentlocation

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

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationManger: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var currentLatLng: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationManger = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object: LocationListener{
            override fun onLocationChanged(location: Location) {
                textView.text = " Location: "+location.latitude.toString()+" , "+location.longitude.toString()
                currentLatLng = LatLng(location.latitude, location.longitude)
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
        locationManger.requestLocationUpdates("gps",5000,0f,locationListener)
        gpsBtn.setOnClickListener{
            if(currentLatLng!=null){
                latText.setText(currentLatLng.latitude.toString())
                lonText.setText(currentLatLng.longitude.toString())
            }
        }
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mapBtn.setOnClickListener{
//            mMap.
            mMap.addMarker(MarkerOptions()
                .position(currentLatLng)
                .title(currentLatLng.toString())
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng))
        }
    }

    override fun onPause() {
        super.onPause()
        locationManger.removeUpdates(locationListener)
        Log.d("GPSStatus", "On Pause!")
    }
}
