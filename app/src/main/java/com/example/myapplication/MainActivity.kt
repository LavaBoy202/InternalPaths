@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication
import com.example.myapplication.Buildings

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        // Top Dropdown Menu

                        // Greeting Text
                        Text("UNIVERSITY OF", fontSize = 30.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold)
                        Text("WATERLOO", fontSize = 30.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold)
                        DropDownMenu(buildings = initBuildings())
                        DropDownMenu(buildings = initBuildings())
                        FilledTonalButtonExample()
                    }
                }
            }
        }
    }
}


@Composable
fun FilledTonalButtonExample() {
    var buttonText by remember { mutableStateOf("Submit") }
    FilledTonalButton(onClick = { buttonText = "Clicked" }, colors = ButtonDefaults.buttonColors(containerColor = Color.Black ) ) {
        Text(buttonText)
    }
}
@Composable
fun Greeting() {
    Text(
        text = "Hi my name is LAVAN",
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting()
    }
}

@Composable
fun DropDownMenu(buildings: Map<String, Buildings>) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(buildings["Davis Center"]?.name ?:"")}
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },

        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                expanded = expanded,
                onDismissRequest = { expanded = false }

            ) {
                buildings.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.key) },
                        onClick = {
                            selectedText = item.key
                            expanded = false
                            Toast.makeText(context, item.key, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}