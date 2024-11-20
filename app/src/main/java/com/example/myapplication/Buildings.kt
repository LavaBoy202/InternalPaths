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
    CPH("Carl A. Pollock Hall (CPH)");

    companion object {
        // Reversing the enum: mapping building name to enum constant
        val reverseLookup: Map<String, BuildingE> = values().associateBy { it.building }
    }
}
fun initBuildings(): Map<String, Buildings> {
    val listOfBuildings = mutableMapOf<String, Buildings>()
    val neighboursMap = mutableMapOf<BuildingE, List<Pair<BuildingE, String>>>()

    //Math Buildings
    neighboursMap[BuildingE.DC] = listOf(BuildingE.MC to "Go to the second or third floor and there will be a bridge to MC", BuildingE.M3 to "Go to the second floor and there will be a bridge to MC", BuildingE.EIT to "Go to the second floor and there will be a bridge to EIT", BuildingE.E3 to "Go to the second floor and there will be a bridge to MC", BuildingE.C2 to "Go to the second floor and find bridge connecting to C2")
    neighboursMap[BuildingE.M3] = listOf(BuildingE.MC to "", BuildingE.DC to "")
    neighboursMap[BuildingE.MC] = listOf(BuildingE.QNC to "Go to the third floor and there will be a bridge to QNC", BuildingE.DC to "Go to the third or fourth floor and there will be a bridge to MC", BuildingE.PAC to "On this floor, there will signs that direct you to PAC. Follow them and take the bridge across.", BuildingE.M3 to "Go to the third floor and there will be a bridge to M3", BuildingE.C2 to "On the first floor find 1082. This will take you to an underground tunnel to C2", BuildingE.SLC to "Go to the third floor there will be a bridge to SLC")

// Engineering Buildings
    neighboursMap[BuildingE.E3] = listOf(BuildingE.DC to "Go down a floor on the staircase and enter the hallway connecting to Davis Center.", BuildingE.E5 to " Go to the 3rd floor and you will find the bridge connecting to E5", BuildingE.E2 to "Go to the second floor of the building and you will find a path connecting to E2.")
    neighboursMap[BuildingE.E2] = listOf(BuildingE.RCH to "On the first floor find 1736, go down the stairs and follow the path to RCH", BuildingE.PHY to "There is a bridge on the 3rd floor connecting to Physics", BuildingE.DWE to "There is a pathway way to DWE on the first floor", BuildingE.E3 to "On the second floor of the building there is a pathway connecting to E3", BuildingE.CPH to "Go to the first floor and follow the pathway to CPH")
    neighboursMap[BuildingE.E5] = listOf(BuildingE.E3 to "Staying on this floor, take the bridge to E3", BuildingE.E7 to "")
    neighboursMap[BuildingE.E6] = listOf(BuildingE.E7 to "")
    neighboursMap[BuildingE.E7] = listOf(BuildingE.E6 to "", BuildingE.E5 to "Go to the third floor of this building")
    neighboursMap[BuildingE.EIT] = listOf(BuildingE.DC to "Go to the third floor and you will find the bridge connection to DC", BuildingE.ESC to "Go to the third floor cross the bridge to ESC abd take the stairs down to the first floor.", BuildingE.PHY to "On the first floor there is a pathway that will take you to Physics.")
    neighboursMap[BuildingE.QNC] = listOf(BuildingE.B2 to "", BuildingE.MC to "")
    neighboursMap[BuildingE.DWE] = listOf(BuildingE.E2 to "On the first floor there is a pathway connecting to E2", BuildingE.RCH to "On the first floor there is pathway to RCH and then take the stair down.")

// Core Sciences Buildings
    neighboursMap[BuildingE.B2] = listOf(BuildingE.STC to "There will be a hallway on the first or second floor that connects to STC", BuildingE.B1 to "", BuildingE.QNC to "")
    neighboursMap[BuildingE.B1] = listOf(BuildingE.ESC to "", BuildingE.B2 to "")
    neighboursMap[BuildingE.ESC] = listOf(BuildingE.EIT to "Go to the third floor and take the connecting bridge to EIT", BuildingE.B1 to "Go to the second floor and will find a path connecting to B1", BuildingE.C2 to "Go to second floor and take the bridge connecting to C2")
    neighboursMap[BuildingE.PHY] = listOf(BuildingE.E2 to "Take the pathway to E2 on the first floor", BuildingE.EIT to "Go to the first floor and go down the hallway to EIT, then take the stairs up")
    neighboursMap[BuildingE.C2] = listOf(BuildingE.MC to "Go to floor 0 and take the underground tunnel. Follow the exit signs along the hallway to get to MC", BuildingE.ESC to "Go to the second floor and take the connecting bridge to ESC", BuildingE.DC to "Go to the third fllor and find the connecting bridge to DC")
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
    neighboursMap[BuildingE.RCH] = listOf(BuildingE.DWE to "There is a hallway that connects to DWE on the first floor.", BuildingE.E2 to "On the second floor there is a pathway that connects to DWE")
    neighboursMap[BuildingE.SLC] = listOf(BuildingE.PAC to "Find the Subway shop or Jugo Juice, PAC will be there", BuildingE.MC to "")
    neighboursMap[BuildingE.PAC] = listOf(BuildingE.SLC to "Walk towards the food court to get to SLC")

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
