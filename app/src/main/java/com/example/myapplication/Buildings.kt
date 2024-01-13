package com.example.myapplication

class Buildings(val name: String) {
    private var buildingName = name;
    fun getName(): String {
        return buildingName;
    }
}

fun initBuildings() {
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
    }
}