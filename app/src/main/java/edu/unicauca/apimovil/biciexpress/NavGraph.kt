package edu.unicauca.apimovil.biciexpress

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Catalog.route) { CatalogScreen(navController) }
        composable(
            route = "bike_detail/{name}/{description}/{imageUrl}",
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            val imageUrl = backStackEntry.arguments?.getString("imageUrl") ?: ""

            BikeDetailScreen(
                navController = navController,
                name = name,
                description = description,
                imageUrl = imageUrl
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }

        composable(Screen.Credits.route) {
            CreditsScreen(navController)
        }
    }
}