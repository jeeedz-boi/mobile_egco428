package com.egco.lec05_listview01

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_main.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_Listview.adapter = myCustomAdapter(this)

    }

    private class myCustomAdapter(context: Context): BaseAdapter(){
        private val mContext: Context
        private val names = arrayListOf("Bob","Jone","Jame","Tom","Nancy")
        private val days = arrayListOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
        init {
            mContext = context
        }

        override fun getCount(): Int {
            return 21
        }

        override fun getItem(position: Int): Any {
            return  position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
//            val whiteColor = Color.parseColor("#FFFFFF")
//            val blackColor = Color.parseColor("#E0E0E0")
            val yellowColor = Color.parseColor("#f0d000")
            val pinkColor = Color.parseColor("#f000b0")
            val greenColor = Color.parseColor("#00f050")
            val orangeColor = Color.parseColor("#f09400")
            val blueColor = Color.parseColor("#00f0dc")
            val purpleColor = Color.parseColor("#8800f0")
            val redColor = Color.parseColor("#f00000")

            val layoutInflator = LayoutInflater.from(mContext)
            val rowMain = layoutInflator.inflate(R.layout.row_main,viewGroup,false)
//            rowMain.name_textView.text = names[position]
            rowMain.name_textView.text = days[position%7]
            rowMain.position_textView.text = "Row Number: $position"

//            if (position%2 == 1){
//                rowMain.setBackgroundColor(whiteColor)
//            }
//            else{
//                rowMain.setBackgroundColor(blackColor)
//            }
            when(position%7){
                0 -> rowMain.setBackgroundColor(yellowColor)
                1 -> rowMain.setBackgroundColor(pinkColor)
                2 -> rowMain.setBackgroundColor(greenColor)
                3 -> rowMain.setBackgroundColor(orangeColor)
                4 -> rowMain.setBackgroundColor(blueColor)
                5 -> rowMain.setBackgroundColor(purpleColor)
                6 -> rowMain.setBackgroundColor(redColor)
            }


            return rowMain

//            val textView = TextView(mContext)
//            textView.text = "Hello World "+(position+1).toString()
//            return textView

        }
    }
}