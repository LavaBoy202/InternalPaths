import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.shadow
import androidx.compose.material3.Icon
import androidx.navigation.NavController

@Composable
fun NavigationInstructionsScreen(navController: NavController) {
    var currentBuilding by remember { mutableStateOf("A") }

    // Function to switch buildings
    fun switchBuilding() {
        currentBuilding = if (currentBuilding == "A") "B" else "A"
    }

    // Instructions based on current building
    val instructions = if (currentBuilding == "A") {
        listOf(
            "Exit Building A" to Icons.Filled.Place,
            "Walk straight until you see the main road" to Icons.Filled.Place,
            "Turn right and continue for 200 meters" to Icons.Filled.ArrowForward,
            "Building B will be on your left" to Icons.Filled.Place
        )
    } else {
        listOf(
            "Exit Building B" to Icons.Filled.Place,
            "Walk straight until you reach the main road" to Icons.Filled.ArrowForward,
            "Turn left and continue for 200 meters" to Icons.Filled.ArrowForward,
            "Building A will be on your right" to Icons.Filled.Place
        )
    }

    // Main container with black background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Black background
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp), // Added horizontal padding
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Navigation Instructions",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White, // White text for contrast
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp) // Spacing below title
            )

            // Instructions card with yellow background and rounded corners
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(10.dp, RoundedCornerShape(20.dp)), // Deep shadow for a floating card effect
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFD166)) // Yellow background
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.Start // Align icons and text properly
                ) {
                    instructions.forEach { (instruction, icon) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black // Icon color
                            )
                            Spacer(modifier = Modifier.width(12.dp)) // Spacing between icon and text
                            Text(
                                text = instruction,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black, // Black text for instructions for better contrast
                                textAlign = TextAlign.Start,
                                lineHeight = 26.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp)) // Larger gap between elements

            // Button to switch buildings with a floating action style
            Button(
                onClick = { navController.navigate("QuickCheckScreen") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF222222), // Dark button color
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50.dp), // Fully rounded button
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(60.dp) // Larger button for better touch area
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Go to Building",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Go Back",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}


