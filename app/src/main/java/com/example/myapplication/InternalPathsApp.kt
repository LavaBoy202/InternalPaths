import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.CampusMapScreen
import com.example.myapplication.IntroductionScreen
import com.example.myapplication.PathViewModel
import com.example.myapplication.QuickCheckScreen
import com.example.myapplication.SplashScreen


@Composable
fun InternalPathsApp() {
    val navController = rememberNavController()
    val viewModel = PathViewModel()
    NavHost(navController = navController, startDestination = "SplashScreen") {
        composable("SplashScreen") { SplashScreen(navController) }
        composable("QuickCheckScreen") {
            QuickCheckScreen(navController = navController, viewModel = viewModel)
        }
        composable("CampusScreen") {
            CampusMapScreen(navController = navController)
        }
        composable("IntroductionScreen") {
            IntroductionScreen(navController = navController)
        }
        composable("NavigationInstructionsScreen"){
            NavigationInstructionsScreen(navController = navController, viewModel = viewModel)
        }

    }
}