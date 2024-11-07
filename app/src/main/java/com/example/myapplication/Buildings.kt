package com.example.myapplication

import android.os.Build
import android.util.Log
import java.util.LinkedList
import java.util.Queue

class Buildings(val name: String) {
    var neighbourBuildings:MutableList<Buildings> = mutableListOf()
    private val directions = mutableMapOf<String, String>()
    fun addNeighbouringBuildings(neighbour: Buildings, instruction: String = ""){
        neighbourBuildings.add(neighbour)
        directions[neighbour.name] = (instruction)
    }
    fun getDirection(building: String): String{
        return directions[building] ?: "Building is not a neighbour"
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
    val neighboursMap = mutableMapOf<BuildingE, List<Pair<BuildingE, String>>>()

    //Math Buildings
    neighboursMap[BuildingE.DC] = listOf(BuildingE.MC to "Go to 2nd floor", BuildingE.M3 to "Go to the 3rd floor", BuildingE.EIT to "Go to 2nd Floor", BuildingE.E3 to "Go to to 2nd floor")
    neighboursMap[BuildingE.M3] = listOf(BuildingE.MC to "", BuildingE.DC to "")
    neighboursMap[BuildingE.MC] = listOf(BuildingE.QNC to "HRLLLLLOOO", BuildingE.DC to "", BuildingE.PAC to "", BuildingE.M3 to "", BuildingE.PAC to "", BuildingE.C2 to "", BuildingE.SLC to "")

// Engineering Buildings
    neighboursMap[BuildingE.E3] = listOf(BuildingE.DC to "", BuildingE.E5 to "", BuildingE.E2 to "")
    neighboursMap[BuildingE.E2] = listOf(BuildingE.RCH to "", BuildingE.PHY to "", BuildingE.DWE to "", BuildingE.E3 to "", BuildingE.CPH to "")
    neighboursMap[BuildingE.E5] = listOf(BuildingE.E3 to "", BuildingE.E7 to "")
    neighboursMap[BuildingE.E6] = listOf(BuildingE.E7 to "")
    neighboursMap[BuildingE.E7] = listOf(BuildingE.E6 to "", BuildingE.E5 to "")
    neighboursMap[BuildingE.EIT] = listOf(BuildingE.DC to "", BuildingE.ESC to "", BuildingE.PHY to "")
    neighboursMap[BuildingE.QNC] = listOf(BuildingE.B2 to "", BuildingE.MC to "")
    neighboursMap[BuildingE.DWE] = listOf(BuildingE.E2 to "", BuildingE.RCH to "")

// Core Sciences Buildings
    neighboursMap[BuildingE.B2] = listOf(BuildingE.STC to "", BuildingE.B1 to "", BuildingE.QNC to "")
    neighboursMap[BuildingE.B1] = listOf(BuildingE.ESC to "", BuildingE.B2 to "")
    neighboursMap[BuildingE.ESC] = listOf(BuildingE.EIT to "", BuildingE.B1 to "", BuildingE.C2 to "")
    neighboursMap[BuildingE.PHY] = listOf(BuildingE.E2 to "", BuildingE.EIT to "")
    neighboursMap[BuildingE.C2] = listOf(BuildingE.MC to "", BuildingE.ESC to "", BuildingE.DC to "")
    neighboursMap[BuildingE.STC] = listOf(BuildingE.NH to "", BuildingE.B2 to "")

// Arts Buildings
    neighboursMap[BuildingE.AL] = listOf(BuildingE.ML to "", BuildingE.EV1 to "", BuildingE.TC to "")
    neighboursMap[BuildingE.HH] = listOf(BuildingE.TC to "", BuildingE.PAS to "")
    neighboursMap[BuildingE.ML] = listOf(BuildingE.AL to "")

// Social Sciences Building
    neighboursMap[BuildingE.PAS] = listOf(BuildingE.HH to "")

// Environments Buildings
    neighboursMap[BuildingE.EV1] = listOf(BuildingE.AL to "", BuildingE.EV2 to "")
    neighboursMap[BuildingE.EV2] = listOf(BuildingE.EV1 to "", BuildingE.EV2 to "")
    neighboursMap[BuildingE.EV3] = listOf(BuildingE.EV2 to "")

// Health Sciences Buildings
    neighboursMap[BuildingE.LHI] = listOf(BuildingE.EXP to "", BuildingE.BMH to "")
    neighboursMap[BuildingE.BMH] = listOf(BuildingE.EXP to "", BuildingE.LHI to "")
    neighboursMap[BuildingE.EXP] = listOf(BuildingE.BMH to "", BuildingE.BMH to "")

// Other
    neighboursMap[BuildingE.RCH] = listOf(BuildingE.DWE to "", BuildingE.E2 to "")
    neighboursMap[BuildingE.SLC] = listOf(BuildingE.PAC to "", BuildingE.MC to "")
    neighboursMap[BuildingE.PAC] = listOf(BuildingE.SLC to "")

    // Initialize buildings and add neighbors
    for (key in BuildingE.entries) {
        val instance = Buildings(key.building)
        listOfBuildings[key.building] = instance
        val neighbours = neighboursMap[key] ?: emptyList()
        neighbours.forEach { (neighbour, instruction) ->
            instance.addNeighbouringBuildings(Buildings(neighbour.building),instruction)
        }
    }
    return listOfBuildings
}
