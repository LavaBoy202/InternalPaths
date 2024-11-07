package com.example.myapplication

import SimpleNavBarScaffold
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay


@Composable
fun QuickCheckScreen(navController: NavController, viewModel: PathViewModel) {
    //SimpleNavBarScaffold(navController, "Quick Check") {
    QuickCheckScreenContent(navController, viewModel)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickCheckScreenContent(navController: NavController, viewModel: PathViewModel) {
    // Variables to hold selected building names
    var selectedText by remember { mutableStateOf("") }
    var selectedText2 by remember { mutableStateOf("") }
    var visible by remember {
        mutableStateOf(true)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
        Column(
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
                FilledTonalButtonExample(selectedText, selectedText2, navController, viewModel )
            }
        }
    }
}
