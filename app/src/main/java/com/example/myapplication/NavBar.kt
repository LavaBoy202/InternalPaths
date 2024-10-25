import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleNavBarScaffold(
    navController: NavController,
    title: String,
    createContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Black,
                contentColor = Color(0xFFFFD166),
                modifier = Modifier.height(60.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(onClick = { navController.navigate("QuickCheckScreen") }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    IconButton(onClick = { navController.navigate("CampusScreen") }) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Map",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        createContent(innerPadding)
    }
}