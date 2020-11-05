package com.egco.lab20_basicsql.Model


class Comment {
    var id: Long = 0
    var commentdata: String? = null
    // Will be used by the ArrayAdapter in the ListView
    override fun toString(): String {
        return commentdata.toString()
    }
}
