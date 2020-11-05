package com.egco.lab19_firestore_list

class Message(val id:String,
              val message: String,
              val rating: Int,
              val timestamp: String){

    constructor(): this("","",0,"")
}