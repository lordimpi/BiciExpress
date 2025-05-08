package edu.unicauca.apimovil.biciexpress.model

data class RegisterRequest(
    val email: String,
    val password: String,
    val confirmPassword: String
)