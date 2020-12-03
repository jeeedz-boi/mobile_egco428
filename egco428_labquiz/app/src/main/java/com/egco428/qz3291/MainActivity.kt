package com.egco428.qz3291

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.egco428.qz3291.Models.Route
import com.egco428.qz3291.Models.RouteProvider.getAllData
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var adapter: RouteAdapter? = null
    private var routeList =  ArrayList<Route>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        /* (1.1) Load data to variable name: routeList. */
        routeList = getAllData(this)

        /* (1.2) Prepare adapter by using RouteAdapter class and set adapter to listView */
        adapter = RouteAdapter(this, R.layout.routes, routeList)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            /* (1.3) Get the selected position and transfer to RouteViewActivity by using displayMap function */
            val intent = Intent(this@MainActivity, RouteViewActivity::class.java)
//            println("position"+ position + id)
            intent.putExtra("position", position.toString())
            startActivity(intent)
        }
        /* (1.4) Update listView when a new route has been added*/
        RouteAdapter(this, R.layout.routes, routeList!!).notifyDataSetChanged()

    }
    private fun displayMap(position: Int) {
        /* (1.3) Get the selected route name and transfer to RouteViewActivity by using displayMap function */
        val intent = Intent(this@MainActivity, RouteViewActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item!!.getItemId();
        if(id == R.id.menuAdd){
            actionAddClickHandler(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun actionAddClickHandler(item: MenuItem) {
        /* (1.5) Go to RouteMapActivity Page by clicking at the + sign on the menu bar */
        val intent = Intent(this@MainActivity, RouteMapActivity::class.java)
        startActivity(intent)
    }

}
