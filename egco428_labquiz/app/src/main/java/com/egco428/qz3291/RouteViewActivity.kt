package com.egco428.qz3291

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.egco428.qz3291.Models.Point
import com.egco428.qz3291.Models.Route
import com.egco428.qz3291.Models.RouteProvider
import com.egco428.qz3291.Models.RouteProvider.getAData
import com.egco428.qz3291.Models.RouteProvider.getAllData

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class RouteViewActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap:GoogleMap
    lateinit var route: Route
    private var routePosition: Int = 0


    lateinit var pointList: MutableList<Point>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_view)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        val bundle = intent.extras
        if(bundle!=null){
            /* (2.1)
                Get the position received from MainActivity
            */
            routePosition = bundle.getString("position")!!.toInt()
        }


        /* (2.2)
           Load a route (routename and all points in this route) at selected position from route.txt by using RouteProvider
        */
        route = getAData(this, routePosition)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        /* (2.3)
            Set up the mapFragment
        */
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


        if(route.point.isNotEmpty()) {
            var i = 0
            var point: LatLng? = null // TODO ?
            for(i in 0..route.point.size-1) {
                /* (2.4)
                    Plot the markers according to selected location and color and draw the line between markers according to the route
                */

                if(route.point[i].color) {
                    mMap.addMarker(MarkerOptions()
                        .position(LatLng(route.point[i].lat,route.point[i].lon))
                        .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED)))
                } else {
                    mMap.addMarker(MarkerOptions()
                        .position(LatLng(route.point[i].lat,route.point[i].lon))
                        .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
                }

                if(i>0){
                    /* (2.5) Draw the line between markers according to the route. */
                     mMap.addPolyline(PolylineOptions()
                    .add(LatLng(route.point[i-1].lat,route.point[i-1].lon) , LatLng(route.point[i].lat,route.point[i].lon))
                    .width(5f)
                    .color(Color.RED))
                }
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(route.point[0].lat,route.point[0].lon), 2f))
        } else{
            Log.d("Marker added: ", "Zero")
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
