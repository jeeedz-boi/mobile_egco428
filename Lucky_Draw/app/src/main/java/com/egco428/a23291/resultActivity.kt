package com.egco428.a23291

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_result.view.*
import kotlin.random.Random

class resultActivity : AppCompatActivity() {
    private var fiveRandomNumber = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

//      Set actionbar as needed
        val actionBar = supportActionBar!!
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.setDisplayShowCustomEnabled(true)
        actionBar.setCustomView(R.layout.result_actionbar)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeAsUpIndicator(R.drawable.back)
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))

//      Random 5 unique numbers and show at resultListTextView
        fiveRandomNumber.clear()
        for (i in 0 until 5) {
            var temp = (20..40).random()
            while(fiveRandomNumber.isNotEmpty()  && fiveRandomNumber.contains(temp)) temp = (20..40).random()
            fiveRandomNumber.add(temp)
        }
        resultListTextView.text = fiveRandomNumber[0].toString()+", "+fiveRandomNumber[1].toString()+", "+fiveRandomNumber[2].toString()+", "+fiveRandomNumber[3].toString()+", "+fiveRandomNumber[4].toString()

//      Assign value from @MainActivity
        val randNumber:String = intent.getStringExtra("rand")!!
        numberTextView.text = randNumber

//      Check if they Win? show You Win!: show You Lose!
        if(fiveRandomNumber.contains(randNumber.toInt())){
            resultPageTextView.text = "You Win!!!"
        }
        else{
            resultPageTextView.text = "You Lose!!!"
            imageView.setImageResource(this.resources.getIdentifier("letter","drawable",this.packageName))
        }

//      Set reDrawBtn go back to @MainActivity
        reDrawBtn.setOnClickListener {
            val intent = Intent(this@resultActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }

//  Set back button functionality
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val myIntent = Intent(applicationContext, MainActivity::class.java)
        startActivityForResult(myIntent, 0)
        return true
    }
}