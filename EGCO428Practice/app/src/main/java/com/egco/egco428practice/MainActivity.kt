package com.egco.egco428practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private var userArray =  ArrayList<UserInfo>()
    private val file: String = "users.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar!!
        actionBar.hide()
        readFile()

        passText.setOnKeyListener { view, keyCode, event ->
            when {

                //Check if it is the Enter-Key,      Check if the Enter Key was pressed down
                ((keyCode == KeyEvent.KEYCODE_ENTER) && (event.action == KeyEvent.ACTION_DOWN)) -> {
                    //perform an action
                    onPerformLogin()
                    //return true
                    return@setOnKeyListener true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        readFile()
    }

    fun onClickSignUpButton(view: View){
        val intent = Intent(this@MainActivity, SignUpActivity::class.java )
        startActivity(intent)
    }

    fun onClickSignInButton(view: View){
        onPerformLogin()
    }

    fun onPerformLogin(){
        val username = userText.text
        val password = passText.text
        var isUserValid = false

        if(username.isEmpty()){
//            userText.error = "Empty Not Allow"
            userText.hint = "Empty Not Allow"
        }
        if(password.isEmpty()){
//            passText.error = "Empty Not Allow"
            passText.hint = "Empty Not Allow"
        }
        for(user in userArray){
            if (user.username == username.toString() && user.password == password.toString()) isUserValid = true
        }

        if(isUserValid){
            Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, UserListActivity::class.java )
            startActivity(intent)
        }
        else{
            Toast.makeText(this, "Login fail", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickCancleButton(view: View){
        userText.text.clear()
        passText.text.clear()
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

}