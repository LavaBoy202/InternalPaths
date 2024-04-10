package com.example.myapplication

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class BuildingsKtTest {

    @Test
    fun testPathExists() {
        val buildings = initBuildings()

        assertTrue(PathExists("DC", "MC", buildings)) // Path should exist
        assertFalse(PathExists("DC", "LIB", buildings)) // Path should not exist
        assertFalse(PathExists("DC", "NonExistentBuilding", buildings)) // Invalid building

        // Add more test cases as needed
    }

    @Test
    fun testDFS() {
        val buildings = initBuildings()

        assertTrue(dfs(buildings["DC"]!!, buildings["MC"]!!, buildings, mutableSetOf())) // Path should exist
        assertFalse(dfs(buildings["DC"]!!, buildings["LIB"]!!, buildings, mutableSetOf())) // Path should not exist
        assertFalse(dfs(buildings["DC"]!!, Buildings("NonExistentBuilding"), buildings, mutableSetOf())) // Invalid building

        // Add more test cases as needed
    }
}