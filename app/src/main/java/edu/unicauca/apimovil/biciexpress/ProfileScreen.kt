package edu.unicauca.apimovil.biciexpress

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    Row(modifier = Modifier.fillMaxSize()) {
        // Menú lateral
        Column(
            modifier = Modifier
                .width(220.dp)
                .fillMaxHeight()
                .background(Color(0xFFE8F2E8))
                .padding(16.dp)
        ) {
            Text("Cuenta", style = MaterialTheme.typography.titleSmall)

            Spacer(modifier = Modifier.height(12.dp))

            listOf(
                "Editar Perfil",
                "Configuración",
                "Favoritos",
                "Ayuda",
                "Acerca de",
                "Privacidad",
                "Log out"
            ).forEach { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        // Contenido del perfil
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text("Santiago Acuña", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = "superuser@user.com",
                onValueChange = {},
                label = { Text("Usuario") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = "**************",
                onValueChange = {},
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Confirmar Contraseña") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { /* guardar cambios */ },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Guardar cambios")
            }
        }
    }
}