package com.egco428.a23291

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SensorEventListener {
    private var randNumber:Int? = null
    private var sensorManager: SensorManager? = null
    private var lastUpdate: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      Set sensor get ready
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lastUpdate = System.currentTimeMillis()

//      Set actionbar as needed
        val actionBar = supportActionBar!!
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.setDisplayShowCustomEnabled(true)
        actionBar.setCustomView(R.layout.custom_actionbar)
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))

//      Set checkResultBtn go back to @resultActivity
        checkResultBtn.setOnClickListener{
            if(randNumber != null){
                val intent = Intent(this@MainActivity, resultActivity::class.java)
                intent.putExtra("rand", randNumber.toString())
                startActivity(intent)
            }
            else{
                Toast.makeText(
                    this,
                    Html.fromHtml("<font color='#fe0000' ><b>" + "Please Shake Your Phone First!!!"+ "</b></font>"),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

//  Enable sensor when App sleep
    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

//  Disable sensor when App sleep
    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) , SensorManager.SENSOR_DELAY_NORMAL)
    }

//    Keep SensorEventListener Happy
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event!!.sensor.type == Sensor.TYPE_ACCELEROMETER){
            getAcceleroMeter(event)
        }
    }

//  Get Accelero (Shake Shake Shake Shake it off!)
    private fun getAcceleroMeter(event: SensorEvent) {
        val value = event.values
        val x = value[0]
        val y = value[1]
        val z = value[2]

        val Accel = (x*x+y*y+z*z)/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH)
        val actualTime = System.currentTimeMillis()

        if (Accel >= 1.5){
            if(actualTime - lastUpdate < 1000){
                return
            }
            randNumber = (20..40).random()
            lastUpdate = actualTime
            numberTextView.text = randNumber.toString()
        }

    }
}


