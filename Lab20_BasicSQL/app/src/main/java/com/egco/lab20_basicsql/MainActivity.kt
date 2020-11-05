package com.egco.lab20_basicsql

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.egco.lab20_basicsql.Model.Comment
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.lang.Exception
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var dataSource: CommentDataSource?  = null
    private var adapter: ArrayAdapter<Comment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataSource = CommentDataSource(this)
        dataSource!!.open()
        val values = dataSource!!.allComments

        adapter = ArrayAdapter<Comment>(this, android.R.layout.simple_list_item_1,values)
        listView.adapter = adapter
    }

    fun onClick(view:View){
        var comment: Comment? = null
        when(view.id){
            R.id.addBtn ->{
                val comments = editMovieName.text.toString()
                if(comments.isEmpty()){
                    editMovieName.error = "Please enter movie name"
                    return
                }
//                val comments = arrayOf("Cool","Cooler","Coolest") // dummy value
//                val nextInt = Random.nextInt(3)
//                comment = dataSource!!.createComment(comments[nextInt])
                comment = dataSource!!.createComment(comments)
                adapter!!.add(comment)
            }
            R.id.deleteBtn ->if(adapter!!.count != 0){
                val index = editIndexText.text.toString()
                if(index.isEmpty()){
                    editIndexText.error = "Please enter movie index"
                    return
                }

                try {
                    comment = adapter!!.getItem(index.toInt()) as Comment
                    dataSource!!.deleteComment(comment)
                    adapter!!.remove(comment)
                }catch (e:Exception){
                    editIndexText.error = "Invalid Index"
                    return
                }
            }
            else{
//                Toast.makeText(this, "No more comment to delete.",Toast.LENGTH_SHORT).show()
//                Toast.makeText(this, Html.fromHtml("<font color='#f25a38' ><b>" + "No more comment to delete." +"</b></font>"),Toast.LENGTH_SHORT).show()
                editIndexText.error = "No more content to delete."
                return
            }
        }
        adapter!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        dataSource!!.open()
    }

    override fun onPause() {
        super.onPause()
        dataSource!!.close()
    }
}