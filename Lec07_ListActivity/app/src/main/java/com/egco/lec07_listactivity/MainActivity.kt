package com.egco.lec07_listactivity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.row_main.view.*

class MainActivity : AppCompatActivity() {
    var data: ArrayList<Course>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        data = DataProvider.getData()
//        val iterator = data.iterator()
//        val builder = StringBuilder()
//
//        while(iterator.hasNext()){
//            val course = iterator.next()
//            builder.append(course.title).append("\n")
//        }
//        dataTextView.text = builder.toString()

//            val courseArrayAdapter = ArrayAdapter<Course>(this,android.R.layout.simple_list_item_1,data!!)
        imageView.setImageResource(this.resources.getIdentifier("egco","drawable",this.packageName))
        courseListView.adapter = myCustomAdapter(data!!,this)

        courseListView.setOnItemClickListener { adapterView, view, position, l ->  
            val course = data!![position]            
            displayDetailCourse(course)
        }
    
    }
    
    private fun displayDetailCourse(course: Course){
        Log.d("Course List", "Course Title: ${course.title} ")
        Log.d("Course List", "Course Title: ${course.credit} ${course.courseNo} ")
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("courseTitle", course.title)
        intent.putExtra("courseDescription", course.description)
        intent.putExtra("courseCredit", course.credit.toString())
        intent.putExtra("courseNo", course.courseNo.toString())
        startActivityForResult(intent, 1)

    }

    private class myCustomAdapter(var objects: ArrayList<Course>?,context: Context): BaseAdapter(){
        private val mContext: Context

        init {
            mContext = context
        }

        override fun getCount(): Int {
            return objects!!.size
        }
        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val rowMain:View
            val course = objects?.get(position)
            if(convertView==null)
            {
                val layoutInflator = LayoutInflater.from(viewGroup!!.context)
                rowMain = layoutInflator.inflate(R.layout.row_main,viewGroup,false)
                Log.d("getView","loading data")
                val viewHolder = ViewHolder(rowMain.imageView2,rowMain.textView2)
                rowMain.tag = viewHolder
            }
            else
            {
                rowMain = convertView
            }

            val whiteColor = Color.parseColor("#FFFFFF")
            val blackColor = Color.parseColor("#E0E0E0")

            val viewHolder = rowMain.tag as ViewHolder

            viewHolder.courseName.text = "$course"

            val imgpos = (position%3)+1
            val res = mContext.resources.getIdentifier("image1010" + imgpos,"drawable", mContext.packageName)
            viewHolder.imageCourse.setImageResource(res)

            if (position%2 == 1){
                rowMain.setBackgroundColor(whiteColor)
            }
            else{
                rowMain.setBackgroundColor(blackColor)
            }

            return rowMain

        }
        private class ViewHolder(val imageCourse: ImageView, val courseName:TextView)
    }
}