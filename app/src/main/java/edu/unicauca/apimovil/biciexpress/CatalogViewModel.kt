package edu.unicauca.apimovil.biciexpress

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf

data class Bicycle(
    val name: String,
    val description: String,
    val imageUrl: String
)

class CatalogViewModel : ViewModel() {
    var bicycles = mutableStateListOf<Bicycle>()
        private set

    init {
        loadBicycles()
    }

    private fun loadBicycles() {
        bicycles.addAll(
            listOf(
                Bicycle(
                    name = "Bicicleta urbana",
                    description = "Color Blanco, Rin 700, Sin cambios",
                    imageUrl = "https://homesale.com.co/cdn/shop/products/roadmaster-storm-bicicletas-roadmaster-710824.jpg?v=1707348003"
                ),
                Bicycle(
                    name = "Bicicleta MTB",
                    description = "Color Gris, Rin 27.5, Cambios: 24v",
                    imageUrl = "https://www.ventoux.com.co/wp-content/uploads/2023/03/R8-R4720-copia.png"
                )
            )
        )
    }
}