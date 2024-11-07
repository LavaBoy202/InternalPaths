package com.example.myapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FilledTonalButtonExample(selectedText: String, selectedText2: String, navController: NavController, viewModel: PathViewModel) {
    var buttonText by remember { mutableStateOf("Search") }
    var isVisible by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showInstructionsSheet by remember { mutableStateOf(false) }

    //LiveData Observing
    val pathExists by viewModel.pathExists.observeAsState(initial = false)
    val path by viewModel.path.observeAsState(initial = emptyList())


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LaunchedEffect(isVisible) {
            if (isVisible) {
                isVisible = false
                showDialog = true
            }
        }
        FilledTonalButton(
            onClick = {
                viewModel.setStartBuilding(selectedText)
                viewModel.setEndBuilding(selectedText2)
                viewModel.findPath(initBuildings())
                isVisible = true
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD166), contentColor = Color.Black)
        ) {
            Text(text = buttonText, fontWeight = FontWeight.Bold)
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth(),) {
                        Icon(
                            imageVector = if (pathExists) Icons.Filled.Check else Icons.Filled.Close,
                            contentDescription = if (pathExists) "Path Found" else "No Path Found",
                            tint = if (pathExists) Color(0xFF2C6E49) else Color(0xFFAE3C3C),
                            modifier = Modifier.size(48.dp)
                        )
                        Text(
                            text = if (pathExists) "Found a Path!" else "Sorry, No Path Found",
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally // Center-aligns the message text
                    ) {
                        Text(
                            text = if (pathExists) {
                                "Looks like there is a path between these two buildings. Would you like to see the instructions?"
                            } else {
                                "We couldn't find a path between these two buildings. Try a different building :("
                            },
                            textAlign = TextAlign.Center // Ensures multi-line text is centered
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                            if (pathExists) {
                                showInstructionsSheet = true
                                navController.navigate("NavigationInstructionsScreen")
                            }
                        }
                    ) {
                        Text(if (pathExists) "Show Instructions" else "Change Selection")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}