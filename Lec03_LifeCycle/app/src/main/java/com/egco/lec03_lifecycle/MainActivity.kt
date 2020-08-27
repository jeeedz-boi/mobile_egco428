package com.egco.lec03_lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("HomePage", "Start Sensor")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomePage","Stop Sensor")
    }
}