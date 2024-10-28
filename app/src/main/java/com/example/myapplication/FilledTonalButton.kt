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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FilledTonalButtonExample(selectedText: String, selectedText2: String) {
    var buttonText by remember { mutableStateOf("Submit") }
    var isVisible by remember { mutableStateOf(false) }
    var pathExists by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showInstructionsSheet by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

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
                val buildings = initBuildings()
                val start = selectedText
                val end = selectedText2
                pathExists = PathExists(start, end, buildings)
                buttonText = "SEARCH"
                isVisible = true
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD166), contentColor = Color.Black)
        ) {
            Text(text = buttonText, fontWeight = FontWeight.Bold)
        }

        AnimatedVisibility(visible = isVisible && pathExists == true) {
            Text(modifier = Modifier.padding(10.dp),text = "Found a Path!", color = Color(0xFF2C6E49), fontWeight = FontWeight.Bold, fontSize = 32.sp)
        }
        AnimatedVisibility(visible = isVisible && pathExists == false && selectedText != "" && selectedText2 != "") {
            Text(modifier = Modifier.padding(10.dp), text = "No Path Found :(", color = Color(0xFFAE3C3C), fontWeight = FontWeight.Bold, fontSize = 32.sp)
        }
        AnimatedVisibility(visible = isVisible && selectedText == "") {
            Text(modifier = Modifier.padding(10.dp), text = "Please Select Start Building", color = Color(0xFFAE3C3C), fontWeight = FontWeight.Bold, fontSize = 28.sp)
        }
        AnimatedVisibility(visible = isVisible && selectedText2 == "") {
            Text(modifier = Modifier.padding(10.dp), text = "Please Select End Building", color = Color(0xFFAE3C3C), fontWeight = FontWeight.Bold, fontSize = 28.sp)
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
//                                coroutineScope.launch { bottomSheetState.show() }
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
//        Image(
//            painter = painterResource(id = R.drawable.goose),
//            contentDescription = "GOOSE",
//            modifier = Modifier
//                .size(150.dp)
//                .padding(18.dp, 0.dp, 0.dp, 10.dp)
//        )
    }
}