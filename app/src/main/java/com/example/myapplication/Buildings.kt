package com.example.myapplication

import android.os.Build

class Buildings(val name: String) {
    var neighbourBuildings:MutableList<Buildings> = mutableListOf()
    fun addNeighbouringBuildings(neighbour: Buildings){
        neighbourBuildings.add(neighbour)
    }
}
enum class BuildingE(val building: String) {
    DC("Davis Center (DC)"),
    QNC("Quantum Nano Center (QNC)"),
    STC("Science Teaching Complex (STC)"),
    ESC("Earth Sciences & Chemistry (ESC)"),
    MC("Mathematics and Computing (MC)"),
    PAC("Physical Activity Center (PAC)"),
    B1("Biology 1 (B1)"),
    B2("Biology 2 (B2)"),
    M3("Mathematics 3 (M3)"),
    EIT("Centre for Environmental Information Technology (EIT)"),
    E2("Engineering 2 (E2)"),
    E3("Engineering 3 (E3)"),
    E5("Engineering 5 (E5)"),
    E6("Engineering 6 (E6)"),
    E7("Engineering 7 (E7)"),
    PHY("Physics"),
    RCH("J.R. Coutts Engineering Lecture Hall"),
    DWE("Douglas Wright Engineering Building (DWE)"),
    AL("Arts Lecture Hall (AL)"),
    BMH("B.C. Matthews Hall (BMH)"),
    SLC("Student Life Centre (SLC)"),
    EV1("Environment 1 (EV3)"),
    EV2("Environment 2 (EV3)"),
    EV3("Environment 3 (EV3)"),
    EXP("Health Expansion Building (EXP)"),
    FED("Federation Hall (FED)"),
    GH("Graduate House (GH)"),
    GSC("General Services Complex (GSC)"),
    HH("J.G. Hagey Hall of the Humanities (HH)"),
    LHI("Lyle S. Hallman Institute for Health Promotion (LHI)"),
    LIB("Dana Porter Library (LIB)"),
    ML("Modern Languages (ML)"),
    NH("Ira G. Needles Hall and Extension (NH)"),
    OPT("School of Optometry and Vision Science (OPT)"),
    PAS("Psychology, Anthropology, Sociology (PAS)"),
    TC("William M. Tatham Centre for Co-operative Education & Career Action (TC)"),
    C2("Chemistry 2 (C2)"),
    CIF("Columbia Icefield (CIF)"),
    CPH("Carl A. Pollock Hall (CPH)"),
}
fun initBuildings(): Map<String, Buildings> {
    val listOfBuildings = mutableMapOf<String, Buildings>()
    val neighboursMap = mutableMapOf<BuildingE, List<BuildingE>>()

    //Neighbours of each building
    neighboursMap[BuildingE.DC] = listOf(BuildingE.MC, BuildingE.M3, BuildingE.EIT, BuildingE.E3)
    neighboursMap[BuildingE.MC] = listOf(BuildingE.QNC, BuildingE.DC, BuildingE.PAC, BuildingE.M3)
    neighboursMap[BuildingE.QNC] = listOf(BuildingE.B2, BuildingE.MC)
    neighboursMap[BuildingE.EIT] = listOf(BuildingE.DC, BuildingE.ESC, BuildingE.PHY)
    neighboursMap[BuildingE.M3] = listOf(BuildingE.MC, BuildingE.DC)
    neighboursMap[BuildingE.E3] = listOf(BuildingE.DC, BuildingE.E5)
    neighboursMap[BuildingE.B2] = listOf(BuildingE.STC, BuildingE.B1, BuildingE.QNC)
    neighboursMap[BuildingE.B1] = listOf(BuildingE.ESC, BuildingE.B2)
    neighboursMap[BuildingE.E2] = listOf(BuildingE.RCH, BuildingE.PHY, BuildingE.DWE)
    neighboursMap[BuildingE.E5] = listOf(BuildingE.E3, BuildingE.E7)
    neighboursMap[BuildingE.E6] = listOf(BuildingE.E7)
    neighboursMap[BuildingE.ESC] = listOf(BuildingE.EIT, BuildingE.B1)
    neighboursMap[BuildingE.PHY] = listOf(BuildingE.E2, BuildingE.EIT)
    neighboursMap[BuildingE.RCH] = listOf(BuildingE.DWE, BuildingE.PHY, BuildingE.E2)
    neighboursMap[BuildingE.DWE] = listOf(BuildingE.E2, BuildingE.RCH)

    // Initialize buildings and add neighbors
    for (key in BuildingE.values()) {
        val instance = Buildings(key.building)
        listOfBuildings[key.building] = instance
        val neighbours = neighboursMap[key] ?: emptyList()
        neighbours.forEach { neighbour ->
            instance.addNeighbouringBuildings(Buildings(neighbour.building))
        }
    }
    return listOfBuildings
}

fun BuildingDFS(startBuilding: Buildings, endingBuilding: Buildings, visited: MutableSet<Buildings>): Boolean{
    if(startBuilding == endingBuilding){
        return true;
    }
    visited.add(startBuilding)
    for (i in startBuilding.neighbourBuildings){
        if (i !in visited){
            if(BuildingDFS(i, endingBuilding, visited)){
                return true;
            }
        }
    }
    return false;
}
fun PathExists(Start: String, End: String, buildings: Map<String, Buildings>) : Boolean{
    if (!buildings.containsKey(Start) || !buildings.containsKey(End)) {
        return false
    }
    val startingBuilding = buildings[Start]!!
    val endingBuilding = buildings[End]!!

    val visited = mutableSetOf<Buildings>()

    return BuildingDFS(startingBuilding, endingBuilding, visited)


}
