@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication
import com.example.myapplication.Buildings

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // Variables to hold selected building names
                var selectedText by remember { mutableStateOf("") }
                var selectedText2 by remember { mutableStateOf("") }
                var visible by remember {
                    mutableStateOf(true)
                }

                Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(20.dp))
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFF000000), shape = RoundedCornerShape(15.dp))
                                .height(300.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "UNIVERSITY",
                                fontSize = 44.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFD166)
                            )
                            Text(
                                text = "OF",
                                fontSize = 44.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFD166)
                            )
                            Text(
                                text = "WATERLOO",
                                fontSize = 44.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFD166)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 36.dp)
                                .clip(shape = RoundedCornerShape(20.dp))
                                .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(15.dp))
                                .fillMaxHeight(),
                        ) {
                            DropDownMenu(buildings = initBuildings(), 1) { text ->
                                selectedText = text
                            }
                            DropDownMenu(buildings = initBuildings(), 2) { text ->
                                selectedText2 = text
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            FilledTonalButtonExample(selectedText, selectedText2)
                            AnimatedVisibility(visible) {
                            }
                        }
                    }
                }
            }
        }
    }
}

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
            modifier = Modifier.size(150.dp).padding(18.dp,0.dp, 0.dp,10.dp)
        )
    }
}


@Composable
fun DropDownMenu(
    buildings: Map<String, Buildings>,
    number: Int,
    onItemSelected: (String) -> Unit // Callback to update selected text
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(buildings["Davis Center"]?.name ?: "FROM") }
    var selectedText2 by remember { mutableStateOf(buildings["Davis Center"]?.name ?: "TO") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(Color.Black),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
        ) {
            TextField(
                value = if (number == 1) selectedText else selectedText2,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(containerColor = Color(0xFFFFD166), focusedIndicatorColor = Color(0xFFCE9E00)),
                modifier = Modifier.menuAnchor(),
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
            )
            ExposedDropdownMenu(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .heightIn(max = 200.dp),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                buildings.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.key) },
                        onClick = {
                            if (number == 1) {
                                selectedText = item.key
                                onItemSelected(item.key)
                            } else {
                                selectedText2 = item.key
                                onItemSelected(item.key)
                            }
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}