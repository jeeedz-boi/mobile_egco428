package com.egco.lec07_listactivity

class Course (val courseNo: Int, val title: String, val description: String, val credit: Double){
    override fun toString(): String {
        return title
    }
}