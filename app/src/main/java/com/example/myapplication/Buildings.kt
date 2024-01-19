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
        "Davis Center (DC)",
        "Quantum Nano Center (QNC)",
        "Science Teaching Complex (STC)",
        "Engineering 2 (E7)",
        "Engineering 3 (E7)",
        "Engineering 5 (E7)",
        "Engineering 6 (E7)",
        "Engineering 7 (E7)",
        "Physical Activity Center (PAC)",
        "Arts Lecture Hall (AL)",
        "B.C. Matthews Hall (BMH)",
        "Biology 1 (B1)",
        "Biology 2 (B2)",
        "Earth Sciences & Chemistry (ESC)",
        "Mathematics and Computing (MC)",
        "Student Life Centre (SLC)",
        "Renison University College(REN)",
        "St. Jerome’s University (STJ)",
        "Health Services (HS)",
        "United College",
        "Conrad Grebel University College(CGR)",
        "Environment 1 (EV3)",
        "Environment 2 (EV3)",
        "Environment 3 (EV3)",
        "Health Expansion Building(EXP)",
        "Federation Hall (FED)",
        "Graduate House (GH)",
        "General Services Complex (GSC)",
        "J.G. Hagey Hall of the Humanities(HH)",
        "Lyle S. Hallman Institute for Health Promotion (LHI)",
       "Dana Porter Library (LIB)",
        "Mathematics 3 (M3)",
        "Modern Languages (ML)",
        "Ira G. Needles Hall and Extension (NH)",
        "School of Optometry and Vision Science (OPT)",
        "Psychology, Anthropology, Sociology (PAS)",
        "Physics (PHY)",
        "J.R. Coutts Engineering Lecture Hall (RCH)",
        "William M. Tatham Centre for Co-operative Education & Career Action (TC)",
        "Arts Lecture Hall (AL)",
        "B.C. Matthews Hall (BMH)",
        "Chemistry 2 (C2)",
        "Columbia Icefield (CIF)",
        "Carl A. Pollock Hall (CPH)",
        "Douglas Wright Engineering Building (DWE)",
        "Centre for Environmental Information Technology (EIT)",
    )
    val listOfBuildings = mutableMapOf<String, Buildings>()
    buildings.forEach { key ->
        val instance = Buildings(key)
        listOfBuildings[key] = instance
        listOfBuildings[key]?.addNeighbouringBuildings(Buildings(key))
    }
    return listOfBuildings
}