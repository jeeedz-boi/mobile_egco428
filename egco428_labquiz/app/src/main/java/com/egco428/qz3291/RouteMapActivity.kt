package com.egco428.qz3291

import android.content.Context

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.egco428.qz3291.Models.Point


import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.android.synthetic.main.activity_route_map.*
import java.util.*
import kotlin.collections.ArrayList

class RouteMapActivity : AppCompatActivity(), OnMapReadyCallback, SensorEventListener {

    private var mMap: GoogleMap? = null
    private var latLng: LatLng? = null
    private var point =  ArrayList<Point>()
    private val file = "routes.txt"
    private var lazyString = ""

    private var sensorManager: SensorManager? = null
    private var lastUpdate: Long = 0
    private var color = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_map)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        point = ArrayList<Point>()
        colorBtn.setBackgroundColor(Color.RED)

        /* (3.1) Set Lat/Lon values from TextViews
           latLng =  ...
        */
        latLng = LatLng(latTextView.text.toString().toDouble(), lonTextview.text.toString().toDouble())

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lastUpdate = System.currentTimeMillis()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        addBtn.setOnClickListener {
            /* (3.2)
               (a) If the colorBtn is red then add the red marker else add the green marker to the Lat/Lon location from textViews.
               (b) Add new location and color to variable point
            */
            if(color) {
                mMap!!.addMarker(MarkerOptions()
                    .position(latLng!!)
                    .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED)))
                point!!.add(Point(latLng!!.latitude, latLng!!.longitude, true))

            }
            else{
                mMap!!.addMarker(MarkerOptions()
                    .position(latLng!!)
                    .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
                point!!.add(Point(latLng!!.latitude, latLng!!.longitude, false))
            }
            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,5f))
        }

        saveBtn.setOnClickListener {
            if(point!!.size >=2 && point != null) {
                var i = 0
                var data = routeText.text.toString()
                while (i< point!!.size){
//                   /* (3.5)
//                      Set value of data as a string according to this format: routename,lat1,lon1,true,lat2,lon2,false,…
//                   */
                    data +=  ","+ point!![i].lat+"," + point!![i].lon+"," + point!![i].color
                    i += 1
                }
                data = data+"\n"
                if (routeText.text.isNullOrEmpty()) {
                    routeText.error = "Please enter a route name"
                } else {

                    try {
                        val fOut = openFileOutput(file, Context.MODE_PRIVATE or Context.MODE_APPEND)
                        fOut.write((data).toByteArray())
                        fOut.close()
                        Toast.makeText(applicationContext, "file saved", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    finish()
                }
            } else {
                Toast.makeText(this, "Please add at least 2 markers", Toast.LENGTH_SHORT).show()
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

    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event)
        }
    }

    private fun getAccelerometer(event: SensorEvent) {
        val values = event.values
        // Movement
        val x = values[0]
        val y = values[1]
        val z = values[2]

        val accel = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)
        val actualTime = System.currentTimeMillis()
        if (accel >= 2)
        //
        {
            if (actualTime - lastUpdate < 200) {
                return
            }
            lastUpdate = actualTime


            /* (3.3)
               While shaking the mobile device, the colorBtn background color will change to Green and Red alternatively
             */
            if(color){
                colorBtn.setBackgroundColor(Color.GREEN)
                color = !color
            }
            else{
                colorBtn.setBackgroundColor(Color.RED)
                color = !color
            }

            /* (3.4)
                Random a new Lat/Lon location by calling randomLocation function and update this Lat/Lon location to textViews
            */
            latLng = randomLocation(latLng!!)
            latTextView.text = latLng!!.latitude.toString()
            lonTextview.text = latLng!!.longitude.toString()
        }
    }

    private fun randomLocation(currntLatLng: LatLng): LatLng{

        var randLat = (Random().nextInt(170)-85).toDouble()
        var randLon = (Random().nextInt(180)-360).toDouble()

        return LatLng(randLat, randLon)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }

    override fun onResume() {
        super.onResume()
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager!!.registerListener(this,
            sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        // unregister listener
        super.onPause()
        sensorManager!!.unregisterListener(this)
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

