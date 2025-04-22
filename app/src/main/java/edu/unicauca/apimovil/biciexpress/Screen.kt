package edu.unicauca.apimovil.biciexpress

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Catalog : Screen("catalog")
    object BikeDetail : Screen("bike_detail/{name}/{description}/{imageUrl}")
    object Profile : Screen("profile")
    object Credits : Screen("credits")
}