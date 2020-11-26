package com.egco.egco428practice

import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlin.random.Random

class SignUpActivity : AppCompatActivity(), SensorEventListener {
    private val file: String = "users.txt"
    private var sensorManager: SensorManager? = null
    private var lastUpdate: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lastUpdate = System.currentTimeMillis()

        val actionBar = supportActionBar!!
        actionBar.hide()

    }

    fun onClickRandomButton(view: View){
        setLatLog()
    }

    fun onClickAddNewUserButton(view: View){
        val username = userSignText.text
        val password = passSignText.text
        val latitude = latText.text
        val longitude = lonText.text

        val csvString = "${username},${password},${latitude},${longitude}"

        try{
            val fOut = openFileOutput(file, Context.MODE_PRIVATE or Context.MODE_APPEND)
            fOut.write((csvString+"\n").toByteArray())
            fOut.close()
            Toast.makeText(this,"Sign Up successfully",Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MapsActivity::class.java)
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK and FLAG_ACTIVITY_CLEAR_TOP and FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun getAcceleroMeter(event: SensorEvent){
        val value = event.values
        val x = value[0]
        val y = value[1]
        val z = value[2]

        val Accel = (x*x+y*y+z*z)/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH)
        val actualTime = System.currentTimeMillis()
        if (Accel >= 1.5){
            if(actualTime - lastUpdate < 200){
                return
            }
            setLatLog()
            lastUpdate = actualTime
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event!!.sensor.type == Sensor.TYPE_ACCELEROMETER){
            getAcceleroMeter(event)
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) , SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    fun setLatLog(){
        val latitude = -85 + Random.nextFloat() * (85 + 85);
        val longitude = -180 + Random.nextFloat() * (180 + 180);

        latText.setText(latitude.toString())
        lonText.setText(longitude.toString())
    }

    fun onBackClickButton(view : View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}