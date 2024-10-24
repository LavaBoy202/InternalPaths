package com.example.myapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun CampusMapScreen() {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(43.4723, -80.5449), 15f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Markers for buildings
        Marker(
            state = MarkerState(position = LatLng(43.4723, -80.5445)),
            title = "Building 1"
        )

        // Polylines for paths
        Polyline(
            points = listOf(
                LatLng(43.4723, -80.5445),
                LatLng(43.4730, -80.5450)
            ),
            color = Color.Blue,
            width = 5f
        )
    }
}