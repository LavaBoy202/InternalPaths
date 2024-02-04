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
    val listOfBuildings = mutableMapOf<String, Buildings>()
    for (key in BuildingE.values()){
        val instance = Buildings(key.building)
        listOfBuildings[key.building] = instance
        var neighbours = mutableListOf<BuildingE>()
        when (key) {
            BuildingE.DC -> {
            var neighbours = mutableListOf<BuildingE>();
            neighbours.add(BuildingE.MC);
            neighbours.add(BuildingE.M3)
            neighbours.add(BuildingE.EIT)
            neighbours.add(BuildingE.E3)
            neighbours.add(BuildingE.DC)
        }
            BuildingE.DC -> {
                var neighbours = mutableListOf<BuildingE>();
                neighbours.add(BuildingE.MC);
                neighbours.add(BuildingE.M3)
                neighbours.add(BuildingE.EIT)
                neighbours.add(BuildingE.E3)
                neighbours.add(BuildingE.DC)
            }
            BuildingE.MC -> {
                var neighbours = mutableListOf<BuildingE>();
                neighbours.add(BuildingE.QNC)
                neighbours.add(BuildingE.DC)
                neighbours.add(BuildingE.PAC)
                neighbours.add(BuildingE.M3)
                neighbours.add(BuildingE.MC)
            }
            BuildingE.QNC -> {
                var neighbours = mutableListOf<BuildingE>()
                neighbours.add(BuildingE.B2)
                neighbours.add(BuildingE.MC)
                neighbours.add(BuildingE.QNC)
            }
            BuildingE.EIT -> {
                var neighbours = mutableListOf<BuildingE>()
                neighbours.add(BuildingE.DC)
                neighbours.add(BuildingE.ESC)
                neighbours.add(BuildingE.PHY)
                neighbours.add(BuildingE.EIT)
            }
            BuildingE.M3 -> {

                neighbours.add(BuildingE.MC)
                neighbours.add(BuildingE.DC)
                neighbours.add(BuildingE.M3)
            }
            BuildingE.E3 -> {
                var neighbours = mutableListOf<BuildingE>()
                neighbours.add(BuildingE.DC)
                neighbours.add(BuildingE.E5)
                neighbours.add(BuildingE.E3)
            }
            BuildingE.B2 -> {
                var neighbours = mutableListOf<BuildingE>()
                neighbours.add(BuildingE.STC)
                neighbours.add(BuildingE.B1)
                neighbours.add(BuildingE.QNC)
                neighbours.add(BuildingE.B2)
            }
            BuildingE.B1 -> {
                var neighbours = mutableListOf<BuildingE>()
                neighbours.add(BuildingE.ESC)
                neighbours.add(BuildingE.B2)
                neighbours.add(BuildingE.B1)
            }
            BuildingE.E2 -> {
                var neighbours = mutableListOf<BuildingE>()
                neighbours.add(BuildingE.RCH)
                neighbours.add(BuildingE.PHY)
                neighbours.add(BuildingE.DWE)
                neighbours.add(BuildingE.E2)
            }
            BuildingE.E5 -> {
                var neighbours = mutableListOf<BuildingE>()
                neighbours.add(BuildingE.E3)
                neighbours.add(BuildingE.E7)
                neighbours.add(BuildingE.E5)
            }
            BuildingE.E6 -> {
                var neighbours = mutableListOf<BuildingE>()
                neighbours.add(BuildingE.E7)
                neighbours.add(BuildingE.E6)
            }
            BuildingE.ESC -> {
                var neighbours = mutableListOf<BuildingE>()
                neighbours.add(BuildingE.EIT)
                neighbours.add(BuildingE.B1)
                neighbours.add(BuildingE.ESC)
            }
            BuildingE.PHY -> {
                var neighbours = mutableListOf<BuildingE>()
                neighbours.add(BuildingE.E2)
                neighbours.add(BuildingE.EIT)
                neighbours.add(BuildingE.PHY)
            }
            BuildingE.RCH -> {
                var neighbours = mutableListOf<BuildingE>()
                neighbours.add(BuildingE.DWE)
                neighbours.add(BuildingE.PHY)
                neighbours.add(BuildingE.E2)
                neighbours.add(BuildingE.RCH)
            }
            BuildingE.DWE -> {
                var neighbours = mutableListOf<BuildingE>()
                neighbours.add(BuildingE.E2)
                neighbours.add(BuildingE.RCH)
                neighbours.add(BuildingE.DWE)
            }
            else -> {
        }

    }
    }
    return listOfBuildings
}