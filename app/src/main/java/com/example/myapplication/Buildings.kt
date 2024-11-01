package com.example.myapplication

import android.os.Build
import android.util.Log
import java.util.LinkedList
import java.util.Queue

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

    //Math Buildings
    neighboursMap[BuildingE.DC] = listOf(BuildingE.MC, BuildingE.M3, BuildingE.EIT, BuildingE.E3)
    neighboursMap[BuildingE.M3] = listOf(BuildingE.MC, BuildingE.DC)
    neighboursMap[BuildingE.MC] = listOf(BuildingE.QNC, BuildingE.DC, BuildingE.PAC, BuildingE.M3, BuildingE.PAC, BuildingE.C2, BuildingE.SLC)

    //Engineering Buildings
    neighboursMap[BuildingE.E3] = listOf(BuildingE.DC, BuildingE.E5, BuildingE.E2)
    neighboursMap[BuildingE.E2] = listOf(BuildingE.RCH, BuildingE.PHY, BuildingE.DWE, BuildingE.E3, BuildingE.CPH)
    neighboursMap[BuildingE.E5] = listOf(BuildingE.E3, BuildingE.E7)
    neighboursMap[BuildingE.E6] = listOf(BuildingE.E7)
    neighboursMap[BuildingE.E7] = listOf(BuildingE.E6, BuildingE.E5)
    neighboursMap[BuildingE.EIT] = listOf(BuildingE.DC, BuildingE.ESC, BuildingE.PHY)
    neighboursMap[BuildingE.QNC] = listOf(BuildingE.B2, BuildingE.MC)
    neighboursMap[BuildingE.DWE] = listOf(BuildingE.E2, BuildingE.RCH)

    //Core Sciences Buildings
    neighboursMap[BuildingE.B2] = listOf(BuildingE.STC, BuildingE.B1, BuildingE.QNC)
    neighboursMap[BuildingE.B1] = listOf(BuildingE.ESC, BuildingE.B2)
    neighboursMap[BuildingE.ESC] = listOf(BuildingE.EIT, BuildingE.B1, BuildingE.C2)
    neighboursMap[BuildingE.PHY] = listOf(BuildingE.E2, BuildingE.EIT)
    neighboursMap[BuildingE.C2] = listOf(BuildingE.MC, BuildingE.ESC,BuildingE.DC)
    neighboursMap[BuildingE.STC] = listOf(BuildingE.NH, BuildingE.B2)

    //Arts Buildings
    neighboursMap[BuildingE.AL] = listOf(BuildingE.ML, BuildingE.EV1,BuildingE.TC)
    neighboursMap[BuildingE.HH] = listOf(BuildingE.TC, BuildingE.PAS)
    neighboursMap[BuildingE.ML] = listOf(BuildingE.AL)

    //Social Sciences Building
    neighboursMap[BuildingE.PAS] = listOf(BuildingE.HH)

    //Environments Buildings
    neighboursMap[BuildingE.EV1] = listOf(BuildingE.AL, BuildingE.EV2)
    neighboursMap[BuildingE.EV2] = listOf(BuildingE.EV1, BuildingE.EV2)
    neighboursMap[BuildingE.EV3] = listOf(BuildingE.EV2)

    //Health Sciences Buildings
    neighboursMap[BuildingE.LHI] = listOf(BuildingE.EXP, BuildingE.BMH)
    neighboursMap[BuildingE.BMH] = listOf(BuildingE.EXP, BuildingE.LHI)
    neighboursMap[BuildingE.EXP] = listOf(BuildingE.BMH, BuildingE.BMH)

    //Other
    neighboursMap[BuildingE.RCH] = listOf(BuildingE.DWE, BuildingE.E2)
    neighboursMap[BuildingE.SLC] = listOf(BuildingE.PAC, BuildingE.MC)
    neighboursMap[BuildingE.PAC] = listOf(BuildingE.SLC)

    // Initialize buildings and add neighbors
    for (key in BuildingE.entries) {
        val instance = Buildings(key.building)
        listOfBuildings[key.building] = instance
        val neighbours = neighboursMap[key] ?: emptyList()
        neighbours.forEach { neighbour ->
            instance.addNeighbouringBuildings(Buildings(neighbour.building))
        }
    }
    return listOfBuildings
}
fun PathExists(start: String, end: String, buildings: Map<String, Buildings>): Boolean {
    val startBuilding = buildings[start] ?: return false
    val endBuilding = buildings[end] ?: return false
    //val buildings2 = initBuildings()
    return bfs(startBuilding, endBuilding, buildings)
}

fun bfs(start: Buildings, endBuilding: Buildings, buildings: Map<String, Buildings>): Boolean {
    val visited = mutableSetOf<String>()
    val queue: Queue<Buildings> = LinkedList()
    val predecessors = mutableMapOf<String, String?>()


    queue.add(start)
    visited.add(start.name)
    predecessors[start.name] = null

    while (queue.isNotEmpty()) {
        val current = queue.poll()
        println("Visiting ${current.name}")
        println("Neighboring buildings of ${current.name}: ${current.neighbourBuildings.map { it.name }}")


        if (current == endBuilding) {
            printPath(start.name, endBuilding.name, predecessors)
            return true
        }


        val neighbors = current.neighbourBuildings.mapNotNull { buildings[it.name] }
        for (neighbor in neighbors) {
            if (!visited.contains(neighbor.name)) {
                queue.add(neighbor)
                visited.add(neighbor.name)
                predecessors[neighbor.name] = current.name 
            }
        }
    }


    println("No path found from ${start.name} to ${endBuilding.name}")
    return false
}

fun printPath(start: String, end: String, predecessors: Map<String, String?>) {
    val path = mutableListOf<String>()
    var current: String? = end


    while (current != null) {
        path.add(current)
        current = predecessors[current]
    }

    path.reverse()
    println("Path from $start to $end: ${path.joinToString(" -> ")}")
}
