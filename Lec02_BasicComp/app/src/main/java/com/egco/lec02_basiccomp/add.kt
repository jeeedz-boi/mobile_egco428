package com.egco.lec02_basiccomp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_home.*

class add : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val bundle = intent.extras
        val txt1:String; val txt2:String; val result:Int

        if(bundle!=null){
            txt1 = bundle.getString("input1").toString()
            txt2 = bundle.getString("input2").toString()
            result = txt1.toInt() + txt2.toInt()
            textInt1.setText(txt1)
            textInt3.setText(txt2)
            resultText.setText(result.toString())
        }
    }
}