package com.egco.lec07_listactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.imageView
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val courseTitle = intent.getStringExtra("courseTitle")
        val courseDesc = intent.getStringExtra("courseDescription")
        val courseCredit = intent.getStringExtra("courseCredit")
        val courseNo = intent.getStringExtra("courseNo")

        titleText.text = courseTitle
        descText.text = courseDesc
        creditTextView.text = "Credit \n$courseCredit"
        courseNoTextView.text = "Course No# \n$courseNo"
        val imgpos = courseNo!!.toInt()%3+1
        imageView.setImageResource(this.resources.getIdentifier("image1010" + imgpos,"drawable",this.packageName))
        Log.d("allText","$courseCredit $courseNo $imgpos")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mene_detail,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}