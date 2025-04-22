package edu.unicauca.apimovil.biciexpress

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState
) {
    val coroutineScope = rememberCoroutineScope()

    ModalDrawerSheet(
        drawerContainerColor = Color(0xFFE8F2E8)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        DrawerMenuItem(
            label = "Configuración",
            icon = Icons.Filled.Settings,
            onClick = {
                coroutineScope.launch { drawerState.close() }
                // navegar a configuración
            }
        )
        DrawerMenuItem(
            label = "Favoritos",
            icon = Icons.Filled.Favorite,
            onClick = {
                coroutineScope.launch { drawerState.close() }
                // Navegar para ir a favoritos
            }
        )
        DrawerMenuItem(
            label = "Ayuda",
            icon = Icons.Filled.Search,
            onClick = {
                coroutineScope.launch { drawerState.close() }
                // Navegar para ayuda
            }
        )
        DrawerMenuItem(
            label = "Acerca de",
            icon = Icons.Filled.Info,
            onClick = {
                coroutineScope.launch { drawerState.close() }
                navController.navigate(Screen.Credits.route)
            }
        )
        DrawerMenuItem(
            label = "Privacidad",
            icon = Icons.Filled.Lock,
            onClick = {
                coroutineScope.launch { drawerState.close() }
                // Aquí puedo navegar o abrir algo
            }
        )
        DrawerMenuItem(
            label = "Log out",
            icon = Icons.Filled.Home,
            onClick = {
                coroutineScope.launch { drawerState.close() }
                navController.navigate(Screen.Login.route) {
                    popUpTo(0) // Limpia el stack de navegación
                }
            }
        )
    }
}