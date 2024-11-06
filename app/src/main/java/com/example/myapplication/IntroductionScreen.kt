package com.example.myapplication
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun IntroductionScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),


    ) {
        Spacer(modifier = Modifier.height(96.dp))
        // Display Image
        Image(
            painter = painterResource(id = R.drawable.group), // Replace with actual image resource
            contentDescription = "Campus Image",
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(24.dp))

        // Title Text
        Text(
            text = "Navigate Paths Between Buildings".uppercase(),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFD166),
            modifier = Modifier.fillMaxWidth(0.75f).padding(horizontal = 20.dp),
            lineHeight = 52.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Description Text
        Text(
            text = "Discover internal paths between buildings to avoid the cold or rain. Select your starting building and where you want to go. ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        FilledTonalButton(
            onClick = { navController.navigate("QuickCheckScreen") },
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = Color(0xFFFFD166),  // Set the background color to yellow
                contentColor = Color.Black     // Set the text color to yellow
            ), modifier = Modifier.padding(top = 24.dp).align(Alignment.CenterHorizontally),
        ) {
            Text( text = "Get Started", fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(6.dp))
        }
    }
}