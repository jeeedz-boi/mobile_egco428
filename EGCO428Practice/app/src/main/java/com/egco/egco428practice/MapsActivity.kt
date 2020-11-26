package com.egco.egco428practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var latitude: String? = null
    private var longtitude: String? = null
    private var username: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        latitude = intent.getStringExtra("latitude")
        longtitude = intent.getStringExtra("longtitude")
        username = intent.getStringExtra("username")
        mapUserText.text = "$username's Location"
        val actionBar = supportActionBar!!
        actionBar.hide()
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
        // Add a marker in Sydney and move the camera
        val userLocation = LatLng(latitude!!.toFloat().toDouble(), longtitude!!.toFloat().toDouble())
        mMap.addMarker(MarkerOptions().position(userLocation).title("$username is here!"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 1.45f))
    }

    fun onBackClickButton(view :View){
        val intent = Intent(this, UserListActivity::class.java)
        startActivity(intent)
    }
}