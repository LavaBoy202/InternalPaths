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
fun initBuildings(): Map<String, Buildings>  {
    /*val buildings = arrayOf(
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
        "Modern Languages (ML)",
        "Ira G. Needles Hall and Extension (NH)",
        "School of Optometry and Vision Science (OPT)",
        "Psychology, Anthropology, Sociology (PAS)",
        "William M. Tatham Centre for Co-operative Education & Career Action (TC)",
        "Arts Lecture Hall (AL)",
        "B.C. Matthews Hall (BMH)",
        "Chemistry 2 (C2)",
        "Columbia Icefield (CIF)",
        "Carl A. Pollock Hall (CPH)",
    )*/
    val listOfBuildings = mutableMapOf<String, Buildings>()
    buildingsE.forEach { key ->
        val instance = Buildings(key)
        listOfBuildings[key] = instance
        listOfBuildings[key]?.addNeighbouringBuildings(Buildings(key))
        when key {
            DC -> {
                var neighbours = mutableListOf<BuildingE>
                neighbours.add(MC)
                neighbours.add(M3)
                neighbours.add(EIT)
                neighbours.add(E3)
                neighbours.forEach{e ->
                val nei = Building(e)}
                
                listOfBuildings[key]?.addNeighbouringBuildings(Buildings(key))
            }
        }
    }
    return listOfBuildings
}