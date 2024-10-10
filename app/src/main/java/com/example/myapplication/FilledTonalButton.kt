package com.example.myapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun FilledTonalButtonExample(selectedText: String, selectedText2: String) {
    var buttonText by remember { mutableStateOf("Submit") }
    var isVisible by remember { mutableStateOf(false) } // State to control visibility of animation
    var pathExists by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LaunchedEffect(isVisible) {
            if (isVisible) {
                delay(3000)
                isVisible = false
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
        Image(
            painter = painterResource(id = R.drawable.goose),
            contentDescription = "GOOSE",
            modifier = Modifier
                .size(150.dp)
                .padding(18.dp, 0.dp, 0.dp, 10.dp)
        )
    }
}