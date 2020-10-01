package com.egco.lab09_actionbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.setImageResource(this.resources.getIdentifier("cat2","drawable",this.packageName))
        imageView.layoutParams.width = 512
    }

    override fun onCreateOptionsMenu(menu: Menu?):Boolean{
        menuInflater.inflate(R.menu.menu_main,menu)
        return  super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id==R.id.actionImage){
            changeImageBois(item)
        }
        return super.onOptionsItemSelected(item)
    }

    fun showSomeToastBois(item:MenuItem){
        Toast.makeText(this,"Subs Bois!",Toast.LENGTH_SHORT).show()
    }

    var flag:Boolean = true
    private fun changeImageBois(item:MenuItem){
        flag = if(flag){
            imageView.setImageResource(this.resources.getIdentifier("cat1","drawable",this.packageName))
            false
        } else{
            imageView.setImageResource(this.resources.getIdentifier("cat2","drawable",this.packageName))
            true
        }

    }

}