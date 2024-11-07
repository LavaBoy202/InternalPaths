package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.LinkedList
import java.util.Queue

class PathViewModel : ViewModel() {

    private val _startBuilding = MutableLiveData<String>()
    val startBuilding: LiveData<String> get() = _startBuilding

    private val _endBuilding = MutableLiveData<String>()
    val endBuilding: LiveData<String> get() = _endBuilding

    private val _path = MutableLiveData<List<String>>()
    val path: LiveData<List<String>> get() = _path

    private val _pathExists = MutableLiveData<Boolean>()
    val pathExists: LiveData<Boolean> get() = _pathExists

    fun setStartBuilding(start: String) {
        _startBuilding.value = start
    }

    fun setEndBuilding(end: String) {
        _endBuilding.value = end
    }

    fun findPath(buildings: Map<String, Buildings>) {
        val start = _startBuilding.value
        val end = _endBuilding.value
        if (start == null || end == null || buildings[start] == null || buildings[end] == null) {
            _pathExists.value = false
            _path.value = emptyList()
            return
        }

        val startBuilding = buildings[start]!!
        val endBuilding = buildings[end]!!
        val predecessors = mutableMapOf<String, String?>()

        if (bfs(startBuilding, endBuilding, buildings, predecessors)) {
            _pathExists.value = true
            _path.value = constructPath(start, end, predecessors)
        } else {
            _pathExists.value = false
            _path.value = emptyList()
        }
    }

    private fun bfs(
        start: Buildings,
        endBuilding: Buildings,
        buildings: Map<String, Buildings>,
        predecessors: MutableMap<String, String?>
    ): Boolean {
        val visited = mutableSetOf<String>()
        val queue: Queue<Buildings> = LinkedList()
        queue.add(start)
        visited.add(start.name)
        predecessors[start.name] = null

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if (current == endBuilding) return true

            val neighbors = current.neighbourBuildings.mapNotNull { buildings[it.name] }
            for (neighbor in neighbors) {
                if (!visited.contains(neighbor.name)) {
                    queue.add(neighbor)
                    visited.add(neighbor.name)
                    predecessors[neighbor.name] = current.name
                }
            }
        }
        return false
    }

    private fun constructPath(start: String, end: String, predecessors: Map<String, String?>): List<String> {
        val path = mutableListOf<String>()
        var current: String? = end
        while (current != null) {
            path.add(current)
            current = predecessors[current]
        }
        path.reverse()
        println("Path from $start to $end: ${path.joinToString(" -> ")}")
        return path
    }
}
