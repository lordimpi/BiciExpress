package edu.unicauca.apimovil.biciexpress.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unicauca.apimovil.biciexpress.model.Bicycle
import edu.unicauca.apimovil.biciexpress.network.RetrofitClient
import kotlinx.coroutines.launch
import androidx.compose.runtime.*

class CatalogViewModel : ViewModel() {

    var bicycles by mutableStateOf<List<Bicycle>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    init {
        loadAvailableBicycles()
    }

    fun loadAvailableBicycles() {
        isLoading = true
        errorMessage = ""

        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getAvailableBicycles()
                if (response.isSuccessful) {
                    bicycles = response.body() ?: emptyList()
                } else {
                    errorMessage = "Error al obtener bicicletas (${response.code()})"
                }
            } catch (e: Exception) {
                errorMessage = "Error: ${e.localizedMessage ?: "Excepción desconocida"}"
            } finally {
                isLoading = false
            }
        }
    }

    fun rentBicycle(id: Int, token: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.rentBicycle("Bearer $token", id)
                if (!response.isSuccessful) {
                    errorMessage = "No se pudo alquilar (${response.code()})"
                }
            } catch (e: Exception) {
                errorMessage = "Error al alquilar: ${e.localizedMessage}"
            }
        }
    }
}
