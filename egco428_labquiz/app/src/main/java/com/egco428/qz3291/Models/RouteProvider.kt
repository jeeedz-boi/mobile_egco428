package com.egco428.qz3291.Models

/*
 * Created by lalita on 24/11/2020 AD.
 */

import android.content.Context
import java.io.*
import java.text.FieldPosition

object RouteProvider{
    private val file = "routes.txt"

    fun getAllData(context: Context): ArrayList<Route> {

        var data = ArrayList<Route>()
        try {
            val fIn = context.openFileInput(file)
            val mfile = InputStreamReader(fIn)
            val br = BufferedReader(mfile)
            var line = br.readLine()
            while (line != null) { // ! =
                var route =  Route("", ArrayList<Point>())
                var item = line.split(",")
                route!!.name = item[0]
                var i = 0
                while(i < item.size-1){
                    route.point.add(Point(item[i+1].toDouble(), item[i+2].toDouble(), item[i+3].toBoolean()))
                    i = i+3
                }
                data.add(route!!)
                line = br.readLine()
            }
            br.close()
            mfile.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return data
    }

    fun getAData(context: Context, position: Int): Route {

        var data = Route("", ArrayList<Point>())
        try {
            val fIn = context.openFileInput(file)
            val mfile = InputStreamReader(fIn)
            val br = BufferedReader(mfile)
            var line = br.readLine()
            var routeCount = 0

            while (line != null) { // ! =
                if(routeCount == position) {
                    var item = line.split(",")
                    data!!.name = item[0]
                    var i = 0
                    while (i < item.size - 1) {
                        data.point.add(
                            Point(
                                item[i + 1].toDouble(),
                                item[i + 2].toDouble(),
                                item[i + 3].toBoolean()
                            )
                        )
                        i = i + 3
                    }
                    return data
                }
                line = br.readLine()
                routeCount++
            }
            br.close()
            mfile.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return data
    }
}