package com.egco.lab19_firestore_list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var dataReference: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitBtn.setOnClickListener{
            submitData()
        }
    }

    private fun submitData() {
        val msg = editText.text.toString()
        if(msg.isEmpty()){
            editText.error = "Please enter message"
            return
        }
        else{
            dataReference = FirebaseFirestore.getInstance()
            var db =  dataReference.collection("dataMessage")
            val messageID = db.document().id
            val messageData = Message(messageID, msg, ratingBar.rating.toInt(), System.currentTimeMillis().toString())

            db.add(messageData)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext, "Message upload successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(applicationContext, "Message upload failefully", Toast.LENGTH_SHORT).show()
                }

        }
    }
}