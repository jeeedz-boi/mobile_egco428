package com.egco428.qz3291

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.egco428.qz3291.Models.Route

class RouteAdapter(val mContext: Context, val layoutResId: Int, val routeList: ArrayList<Route>):
    ArrayAdapter<Route>(mContext, layoutResId, routeList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflator: LayoutInflater = LayoutInflater.from(mContext)
        val view: View = layoutInflator.inflate(layoutResId, null)
        val routeTextView = view.findViewById<TextView>(R.id.routeVw)
        val rt = routeList[position]

        routeTextView.text = rt.name
        return view
    }
}