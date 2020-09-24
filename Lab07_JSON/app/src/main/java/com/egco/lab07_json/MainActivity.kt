package com.egco.lab07_json

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.egco.lab07_json.Adapter.FeedAdapter
import com.egco.lab07_json.Model.RSSObject
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private val RSS_Link = "http://rss.nytimes.com/services/xml/rss/nyt/Science.xml"
    private val RSS_to_JSON_API = "https://api.rss2json.com/v1/api.json?rss_url="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayOutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)
        recycleView.layoutManager = linearLayOutManager
//        loadRSS()
    }
//    private fun loadRSS(){
//        val client = OkHttpClient()
//        val loadRSSAsynce = object:AsyncTask<String, String, String>() {
//            override fun onPreExecute() {
//                Toast.makeText(this@MainActivity,"Please Wait...",Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onPostExecute(result: String?) {
//                super.onPostExecute(result)
//                var rssObject:RSSObject
//                rssObject = Gson().fromJson<RSSObject>(result,RSSObject::class.java)
//                val adapter = FeedAdapter(rssObject,baseContext)
//                recycleView.adapter = adapter
//                adapter.notifyDataSetChanged()
//                Log.d("Result-Tag","Load data to adapter")
//            }
//
//            override fun doInBackground(vararg params: String): String {
//                val builder = Request.Builder()
//                builder.url(params[0])
//                val request = builder.build()
//                try {
//                    val res = client.newCall(request).execute()
//                    return  res.body!!.string()
//                }
//                catch (err:Exception){
//                    err.printStackTrace()
//                }
//                return ""
//            }
//
//
//        }
//        val url_get_data = StringBuilder(RSS_to_JSON_API)
//        url_get_data.append(RSS_Link)
//        loadRSSAsynce.execute(url_get_data.toString())
//    }
}