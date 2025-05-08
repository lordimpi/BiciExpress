package edu.unicauca.apimovil.biciexpress.model

data class Bicycle(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val available: Boolean,
    val type: String,
    val pricePerHour: Double,
    val usuarioId: Int?,
    val usuario: Any?,
    val latitude: Double,
    val longitude: Double
)