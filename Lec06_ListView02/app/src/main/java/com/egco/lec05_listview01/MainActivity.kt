package com.egco.lec05_listview01

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_main.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val names = arrayListOf("Bob","Jone","Jame","Tom","Nancy","Harry","Ross","Bruce","Cook","Carolyn","Morgan","Albert","Walker","Randy","Reed","Larry","Barnes","Lois","Wilson","Jesse")
        main_Listview.adapter = myCustomAdapter(names)

        main_Listview.setOnItemClickListener { adapterView, view, position, id ->
            val item = adapterView.getItemAtPosition(position) as String
            Toast.makeText(this,"${item} $position",Toast.LENGTH_SHORT).show()
        }
        addBtn.setOnClickListener{
            if(editText.text.toString()!=null){
                names.add(editText.text.toString())
                myCustomAdapter(names).notifyDataSetChanged()
            }
        }
    }

    private class myCustomAdapter(array:ArrayList<String>): BaseAdapter(){
//        private val mContext: Context
//        private val names = arrayListOf("Bob","Jone","Jame","Tom","Nancy","Harry","Ross","Bruce","Cook","Carolyn","Morgan","Albert","Walker","Randy","Reed","Larry","Barnes","Lois","Wilson","Jesse")
//        private val days = arrayListOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
//        init {
//            mContext = context
//        }
        private val names:ArrayList<String> = array

        override fun getCount(): Int {
            return 5
        }

        override fun getItem(position: Int): Any {
            return  names[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val rowMain:View
            val whiteColor = Color.parseColor("#FFFFFF")
            val blackColor = Color.parseColor("#E0E0E0")
//            val yellowColor = Color.parseColor("#f0d000")
//            val pinkColor = Color.parseColor("#f000b0")
//            val greenColor = Color.parseColor("#00f050")
//            val orangeColor = Color.parseColor("#f09400")
//            val blueColor = Color.parseColor("#00f0dc")
//            val purpleColor = Color.parseColor("#8800f0")
//            val redColor = Color.parseColor("#f00000")
            if(convertView==null)
            {
                val layoutInflator = LayoutInflater.from(viewGroup!!.context)
                rowMain = layoutInflator.inflate(R.layout.row_main,viewGroup,false)
                Log.d("getView","loading data")
                val viewHolder = ViewHolder(rowMain.name_textView,rowMain.position_textView)
                rowMain.tag = viewHolder
            }
            else
            {
                rowMain = convertView
            }

            val viewHolder = rowMain.tag as ViewHolder
            viewHolder.nameTextView.text = names[position]
            viewHolder.positionTextView.text = "Row number: $position"
//            val layoutInflator = LayoutInflater.from(mContext)
//            val rowMain = layoutInflator.inflate(R.layout.row_main,viewGroup,false)
//            rowMain.name_textView.text = names[position]
////            rowMain.name_textView.text = days[position%7]
//            rowMain.position_textView.text = "Row Number: $position"

            if (position%2 == 1){
                rowMain.setBackgroundColor(whiteColor)
            }
            else{
                rowMain.setBackgroundColor(blackColor)
            }

            rowMain.setOnClickListener{

                if(names.size >= count)
                {
                    rowMain.animate().setDuration(500).alpha(0F).withEndAction {
                        names.removeAt(position)
                        notifyDataSetChanged()
                        rowMain.alpha = 1F
                    }
                    Log.d("name size","${names.size}")
                }
                else
                {
                    Log.d("Error","Index Out of Bound")
                }

            }

//            rowMain.setOn
//            when(position%7){
//                0 -> rowMain.setBackgroundColor(yellowColor)
//                1 -> rowMain.setBackgroundColor(pinkColor)
//                2 -> rowMain.setBackgroundColor(greenColor)
//                3 -> rowMain.setBackgroundColor(orangeColor)
//                4 -> rowMain.setBackgroundColor(blueColor)
//                5 -> rowMain.setBackgroundColor(purpleColor)
//                6 -> rowMain.setBackgroundColor(redColor)
//            }


            return rowMain

//            val textView = TextView(mContext)
//            textView.text = "Hello World "+(position+1).toString()
//            return textView

        }
        private class ViewHolder(val nameTextView:TextView, val positionTextView:TextView)
    }
}