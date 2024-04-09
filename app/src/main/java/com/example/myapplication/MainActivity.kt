@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication
import com.example.myapplication.Buildings

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // Variables to hold selected building names
                var selectedText by remember { mutableStateOf("Davis Center") }
                var selectedText2 by remember { mutableStateOf("Davis Center") }

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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FilledTonalButton(
            onClick = {
                val startBuilding = selectedText
                val targetBuilding = selectedText2
                val Buildings = initBuildings()

                val pathExists = PathExists(startBuilding, targetBuilding, Buildings)
                buttonText = pathExists.toString()
                Log.i("PathExists", "Path exists between $startBuilding and $targetBuilding: $pathExists")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD166), contentColor = Color.Black)
        ) {
            Text(text = buttonText, fontWeight = FontWeight.Bold)
        }
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
                                onItemSelected(item.key) // Update selected text using the callback
                            } else {
                                selectedText2 = item.key
                                onItemSelected(item.key) // Update selected text using the callback
                            }
                            expanded = false
                            //Toast.makeText(context, item.key, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}