package com.example.lec08_jsonokhttp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lec08_jsonokhttp.model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
//    private val jsonURL: String = "https://egco428-json.firebaseio.com/movies/1.json"
    private var jsonURL: String = "https://egco428-json.firebaseio.com/movies/1.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        callBtn.setOnClickListener{
            val client = OkHttpClient()
            var asyncTask = object:AsyncTask<String, String, String>(){
                override fun onPreExecute() {
                    Toast.makeText(this@MainActivity,"Requset to sent. Pleast wait...",Toast.LENGTH_SHORT).show()
                }

                override fun doInBackground(vararg params: String?): String {
                    val builder = Request.Builder()
//                    Log.d("param","${params[0]}")
                    builder.url(params[0].toString())
                    val request = builder.build()
                    try
                    {
                        val response = client.newCall(request).execute()
                        val randomValue = Random.nextInt(0,5)
                        jsonURL = "https://egco428-json.firebaseio.com/movies/$randomValue.json"
                        return  response.body!!.string()
                    }
                    catch(e:Exception)
                    {
                        e.printStackTrace()
                    }
                    return ""
                }

                override fun onPostExecute(result: String?) {
                    val movieText = Gson().fromJson(result,Movie::class.java)
                    Log.d("result=","$movieText")
                    movieTextView.text = movieText.name
                    bubbleView_name.text = movieText.name
                    bubbleView_star.text = movieText.Star
                    bubbleView_genre.text = movieText.Genre
                    bubbleView_year.text = movieText.Year
                }
            }
            asyncTask.execute(jsonURL)
        }
    }
}