package pruebajj
//package edu.unicauca.navigationaplimovil

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

class DataApp {
    companion object {
        fun getScreensData(): List<ScreenData> {
            return listOf(
                ScreenData(
                    text = "Pantalla 1",
                    textButton = "Entrar",
                    icon = Icons.Default.AccountCircle,
                    color = Color(0xFF808080)
                ),
                ScreenData(
                    text = "Catálogo",
                    textButton = "Siguiente",
                    icon = Icons.Default.ArrowForward,
                    color = Color.White,
                    isListScreen = true,
                    bikesList = listOf(
                        BikeData(
                            name = "Bicicleta urbana",
                            description = "Color Blanco, Rin 700, Sin cambios",
                            imageId = R.drawable.urbann,
                            duration = "Hoy • 23 min"
                        ),
                        BikeData(
                            name = "Bicicleta MTB",
                            description = "Color Gris, Rin 27.5, Cambios 24v",
                            imageId = R.drawable.trochera,
                            duration = "Hoy • 21 min"
                        ),
                        BikeData(
                            name = "BMX",
                            description = "Color Negro, Rin 20, Sin cambios",
                            imageId = R.drawable.bmx,
                            duration = "Hoy • 15 min"
                        ),
                        BikeData(
                            name = "Rutera",
                            description = "Color Azul, Rin 700, Cambios 21v",
                            imageId = R.drawable.rutera,
                            duration = "Hoy • 18 min"
                        ),
                        BikeData(
                            name = "Mountain_bike",
                            description = "Color Verde, Rin 29, Cambios 27v",
                            imageId = R.drawable.mountain_bike,
                            duration = "Hoy • 20 min"
                        )
                    )
                ),
                ScreenData(
                    text = "Pantalla 3",
                    textButton = "Guardar",
                    icon = Icons.Default.AccountCircle,
                    color = Color(0xFF0057FF),
                    isProfileScreen = true
                ),
                ScreenData(
                    text = "Pantalla 4",
                    textButton = "Inicio",
                    icon = Icons.Default.Home,
                    color = Color(0xFF0057FF),
                    backgroundImageId = R.drawable.logo
                )
            )
        }
    }
}

data class BikeData(
    val name: String,
    val description: String,
    val imageId: Int? = null,
    val duration: String
)

data class ScreenData(
    val text: String,
    val textButton: String,
    val icon: ImageVector,
    val color: Color,
    val backgroundImageId: Int? = null,
    val isListScreen: Boolean = false,
    val isProfileScreen: Boolean = false,
    val bikesList: List<BikeData> = emptyList()
)