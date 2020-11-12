package com.egco.lab21_basicmap

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
        val sydney = LatLng(-34.0, 151.0)
        val mu  = LatLng(13.7946, 100.3234)

//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(mu).title("Marker in Mahidol").snippet("Content"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mu, 2f))

        val polyList = arrayListOf<LatLng>()

        mMap.setOnMapClickListener { LatLng ->
//            mMap.animateCamera(CameraUpdateFactory.newLatLng(LatLng))
            polyList.add(LatLng)

            if(polyList.isNotEmpty()){
                mMap.addPolyline(PolylineOptions()
                    .addAll(polyList)
                    .width(5f)
                    .color(Color.MAGENTA)
                )

                mMap.addMarker(MarkerOptions()
                    .position(LatLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.puppy_2))
                    .title(LatLng.toString())
                    .snippet("Content")
                )
            }

        }


//        mMap.addPolyline(PolylineOptions()
//            .add(LatLng(15.00, 100.45612), LatLng(15.5234, 99.8434), LatLng(15.942, 98.0), LatLng(15.00, 100.45612))
//            .width(5f)
//            .color(Color.MAGENTA)
//        )
//
//        mMap.addPolygon(PolygonOptions()
//            .add(LatLng(15.00, 100.45612), LatLng(15.5234, 99.8434), LatLng(15.942, 98.0), LatLng(15.00, 100.45612))
//            .strokeColor(Color.RED)
//            .fillColor(Color.MAGENTA)
//        )

        mMap.setOnMapLongClickListener { LatLng ->
//            mMap.addMarker(MarkerOptions()
//                .position(LatLng)
//                .title(LatLng.toString())
//                .snippet("Content"))
            mMap.addMarker(MarkerOptions()
                .position(LatLng)
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.puppy_2))
                .title(LatLng.toString())
                .snippet("Content")
            )
        }

    }
}