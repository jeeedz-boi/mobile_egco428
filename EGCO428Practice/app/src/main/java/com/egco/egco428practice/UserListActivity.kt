package com.egco.egco428practice

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_user_list.*
import kotlinx.android.synthetic.main.userslist.view.*
import java.io.BufferedReader
import java.io.InputStreamReader
import android.view.View as View1

class UserListActivity : AppCompatActivity() {
    private var userArray =  ArrayList<UserInfo>()
    private val file: String = "users.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        readFile()
        listView.adapter = myCustomAdapter(this, userArray!!)

        listView.setOnItemClickListener{adapterView, view, position, l ->
            val user = userArray!![position]
            displayUserLocation(user)
        }

        val actionBar = supportActionBar!!
        actionBar.hide()

    }

    private fun displayUserLocation(user: UserInfo){
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("latitude", user.latitude)
        intent.putExtra("longtitude", user.longtitude)
        intent.putExtra("username", user.username)
        startActivity(intent)
    }

    private class myCustomAdapter(context: Context, arr: ArrayList<UserInfo>) : BaseAdapter() {
        private val mContext: Context
        val data: ArrayList<UserInfo> = arr

        init {
            mContext = context
        }

        override fun getView(position: Int, convertView: View1?, viewGroup: ViewGroup?): View1 {
            val rowMain: View1
            if (convertView == null) {
                val layoutInflater = LayoutInflater.from(mContext)
                rowMain = layoutInflater.inflate(R.layout.userslist, viewGroup, false)
            } else {
                rowMain = convertView
            }
            rowMain.userTextView.text = data[position].username

            return rowMain
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return data.count()
        }
    }

    fun readFile() {
        try {
            val fIn = openFileInput(file)
            val mFile = InputStreamReader(fIn)
            val buffer = BufferedReader(mFile)
            var line = buffer.readLine()
            while (line != null) {
                var user = line.split(",")
                userArray.add(UserInfo(user[0],user[1],user[2],user[3]))
                line = buffer.readLine()
            }
            buffer.close()
            mFile.close()
            fIn.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun onBackClickButton(view : android.view.View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}