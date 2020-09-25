package com.example.lec07_rippleview

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        glView.run {
            addBackgroundImages(listOf(BitmapFactory.decodeResource(resources,R.drawable.sky),BitmapFactory.decodeResource(resources,R.drawable.sunset)))
            setRippleOffset(0.01F)
            setFadeInterval(2000)
            startCrossFadeAnimation()
        }

    }
}