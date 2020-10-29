package com.egco.lab18_fireimage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val PICK_IMAGE_REQUEST = 1
    private var filePath: Uri? = null
    internal var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storageReference = FirebaseStorage.getInstance().reference

        chooseBtn.setOnClickListener(this)
        UploadBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if(view === chooseBtn){
            showFileChooser()
        }
        else if(view === UploadBtn){
            uploadFile()
        }
    }

    private fun uploadFile() {
        if(filePath != null){

//            val imageReference = storageReference!!.child("images/"+UUID.randomUUID().toString())
            val msg = editText.text.toString()
            if(msg.isEmpty()){
                editText.error = "Please enter path"
                return
            }

            Toast.makeText(applicationContext, "Uploading...", Toast.LENGTH_SHORT).show()
            val imageReference = storageReference!!.child(msg+"/"+UUID.randomUUID().toString())

            imageReference.putFile(filePath!!)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext, "Image upload successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(applicationContext, "Image upload failed", Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener {taskSnapShot ->
                    val progression = 100*taskSnapShot.bytesTransferred/taskSnapShot.totalByteCount

                    Toast.makeText(applicationContext, "Uploading ${progression.toInt()}%", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun showFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select a Photo"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null){
            filePath = data.data
            try{
                val bitmap  = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imageView.setImageBitmap(bitmap)
            }catch (e: IOException){
                e.printStackTrace()
            }

        }
    }
}