package edu.unicauca.apimovil.biciexpress.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import edu.unicauca.apimovil.biciexpress.network.RetrofitClient
import edu.unicauca.apimovil.biciexpress.model.LoginRequest

class AuthViewModel : ViewModel() {
    var token by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    var showDialog by mutableStateOf(false)
        private set

    fun login(email: String, password: String) {
        isLoading = true
        errorMessage = ""

        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    token = response.body()?.token ?: ""
                    if (token.isBlank()) {
                        errorMessage = "Token vacío. Revisa la respuesta del servidor."
                        showDialog = true
                    }
                } else {
                    errorMessage = "Usuario o Contraseña incorrectos"
                    showDialog = true
                }
            } catch (e: Exception) {
                errorMessage = "Error: ${e.localizedMessage ?: "Excepción desconocida"}"
                showDialog = true
            } finally {
                isLoading = false
            }
        }
    }

    fun dismissDialog() {
        showDialog = false
    }
}