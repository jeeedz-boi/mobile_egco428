package com.egco.lab15_accelerometer

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var color = false
    private var view:View? = null
    private var lastUpdate: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shakeText.setBackgroundColor(Color.BLUE)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lastUpdate = System.currentTimeMillis()
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) , SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent) {
        if(event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            getAcceleroMeter(event)
        }
    }
    private fun getAcceleroMeter(event: SensorEvent){
        val value = event.values
        val x = value[0]
        val y = value[1]
        val z = value[2]

        val Accel = (x*x+y*y+z*z)/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH)
        val actualTime = System.currentTimeMillis()
        if (Accel >= 2){
            if(actualTime - lastUpdate < 200){
                return
            }
            lastUpdate = actualTime
            Toast.makeText(this, "Device is shuffled", Toast.LENGTH_SHORT).show()
            if(color){
                shakeText.setBackgroundColor(Color.RED)
            }
            else{
                shakeText.setBackgroundColor(Color.BLUE)
            }
            color = !color
        }
    }
}