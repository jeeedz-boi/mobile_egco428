package com.egco.lab19_firestore_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class MessageAdapter(val mContext:Context, val layoutId: Int, val messagelist: List<Message>) :ArrayAdapter<Message>(mContext, layoutId, messagelist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflator: LayoutInflater = LayoutInflater.from(mContext)
        val view: View = layoutInflator.inflate(layoutId, null)
        val msgTextView  =  view.findViewById<TextView>(R.id.msgView)
        val ratingTextView  =  view.findViewById<TextView>(R.id.ratingView)
        val idTextView  =  view.findViewById<TextView>(R.id.idView)
        val msg = messagelist[position]
        msgTextView.text = "Message: "+msg.message
        ratingTextView.text = msg.rating.toString()
        idTextView.text ="ID: "+ msg.id


        return view
    }

}