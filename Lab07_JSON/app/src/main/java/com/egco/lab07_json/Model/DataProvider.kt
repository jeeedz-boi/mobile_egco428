package com.egco.lab07_json.Model

import kotlin.collections.ArrayList
object DataProvider {
    private val data = ArrayList<Movies>()
    fun getData(): ArrayList<Movies>{
        return data
    }

    init {
        data.add(Movies("Starwars: New Hope","Luke Skywalker", "1980", "Adventure"))
        data.add(Movies("Starwars: The Empire Strike Back", "Hun Solo", "1982", "Adventure"))
        data.add(Movies("Matrix", "Morpheus", "2000","Action"))
        data.add(Movies("Avenger", "Ironman", "2010", "Action"))
        data.add(Movies("Indiana Jones", "Dr. Jones","1984", "Adventure"))
        data.add(Movies("Starwars: Return of the Jedi","Obi-one", "1985", "Adventure"))
    }
}