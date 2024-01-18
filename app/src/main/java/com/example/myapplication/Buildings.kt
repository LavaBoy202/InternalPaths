package com.example.myapplication

import android.os.Build

class Buildings(val name: String) {
    var neighbourBuildings:MutableList<Buildings> = mutableListOf()
    fun addNeighbouringBuildings(neighbour: Buildings){
        neighbourBuildings.add(neighbour)
    }
}

fun initBuildings(): Map<String, Buildings>  {
    val buildings = arrayOf(
        "Davis Center",
        "Quantum Nano Center",
        "Science Teaching Complex",
        "Engineering 7",
        "Physical Activity Center",
        "Arts Lecture Hall (AL)",
        "B.C. Matthews Hall (BMH)",
        "Biology 1 (B1)",
        "Biology 2 (B2)",
        "Earth Sciences & Chemistry (ESC)"
    )
    val listOfBuildings = mutableMapOf<String, Buildings>()
    buildings.forEach { key ->
        val instance = Buildings(key)
        listOfBuildings[key] = instance
        listOfBuildings[key]?.addNeighbouringBuildings(Buildings(key))
    }
    return listOfBuildings
}