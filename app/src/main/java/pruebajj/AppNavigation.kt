package pruebajj
//package edu.unicauca.navigationaplimovil
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.name
    ) {
        composable(route = Screens.HomeScreen.name) {
            ScreenButton(
                data = DataApp.getScreensData()[0],
                onClickButton = { navController.navigate(Screens.SecondScreen.name) }
            )
        }
        composable(route = Screens.SecondScreen.name) {
            ScreenButton(
                data = DataApp.getScreensData()[1],
                onClickButton = { navController.navigate(Screens.FinalScreen.name) },
                onBackClick = { navController.navigate(Screens.HomeScreen.name) }
            )
        }
        composable(route = Screens.FinalScreen.name) {
            ScreenButton(
                data = DataApp.getScreensData()[2],
                onClickButton = { navController.navigate(Screens.HomeScreen.name) }
            )
        }
    }
}

enum class Screens {
    HomeScreen,
    SecondScreen,
    FinalScreen
}