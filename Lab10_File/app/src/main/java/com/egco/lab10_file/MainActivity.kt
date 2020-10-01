package com.egco.lab10_file

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.InputDevice
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private var data: String? = null
    private val file: String = "mydata.txt"
    private var adapter: ArrayAdapter<String>? = null
    private var arrayAll = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //        solution #1
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1)
        viewList.adapter = adapter
    }

    fun saveFile(view: View){
        data = inputText.text.toString()
        try{
            val fOut = openFileOutput(file, Context.MODE_PRIVATE or Context.MODE_APPEND)
            fOut.write((data!!+"\n").toByteArray())
            fOut.close()
            Toast.makeText(this,"file saved successfully",Toast.LENGTH_SHORT).show()
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun readFile(view: View){
        var all = StringBuilder()
        try {
            adapter!!.clear()
//            arrayAll.clear()
            val fIn = openFileInput(file)
            val mFile = InputStreamReader(fIn)
            val buffer = BufferedReader(mFile)
            var line = buffer.readLine()

            while(line != null){

                adapter!!.add(line)
//                arrayAll.add(line)
                all.append(line+"\n")
                line = buffer.readLine()
            }
            buffer.close()
            mFile.close()
            fIn.close()
        }
        catch (e:Exception){
            e.printStackTrace()
        }

//        //        solution #2
//        val arrayAdapter: ArrayAdapter<*>
//        var mListView = findViewById<ListView>(R.id.viewList)
//        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayAll)
//        mListView.adapter = arrayAdapter

//        inputText.setText(all)
        Toast.makeText(this,"file read successfully",Toast.LENGTH_SHORT).show()

    }
}