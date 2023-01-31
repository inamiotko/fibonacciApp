import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.fibonacciapp.navigation.Screen
import com.example.fibonacciapp.screens.FibonacciNumberScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.FibonacciNumberScreen.route) {
        composable(route = Screen.FibonacciNumberScreen.route) {
            FibonacciNumberScreen()
        }
    }
}