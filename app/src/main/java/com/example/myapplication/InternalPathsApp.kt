import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.CampusMapScreen
import com.example.myapplication.IntroductionScreen
import com.example.myapplication.QuickCheckScreen


@Composable
fun InternalPathsApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "IntroductionScreen") {
        composable("QuickCheckScreen") {
            QuickCheckScreen(navController = navController)
        }
        composable("CampusScreen") {
            CampusMapScreen(navController = navController)
        }
        composable("IntroductionScreen") {
            IntroductionScreen(navController = navController)
        }
        composable("NavigationInstructionsScreen"){
            NavigationInstructionsScreen(navController = navController)
        }

    }
}