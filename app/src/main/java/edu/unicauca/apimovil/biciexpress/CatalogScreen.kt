package edu.unicauca.apimovil.biciexpress

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(navController: NavController, catalogViewModel: CatalogViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Catálogo de Bicicletas") })
        }
    ) { innerPadding ->

        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(catalogViewModel.bicycles) { bicycle ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    onClick = {
                        val encodedName = Uri.encode(bicycle.name)
                        val encodedDesc = Uri.encode(bicycle.description)
                        val encodedUrl = Uri.encode(bicycle.imageUrl)

                        navController.navigate(
                            "bike_detail/$encodedName/$encodedDesc/$encodedUrl"
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = bicycle.imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .padding(end = 12.dp)
                        )

                        Column {
                            Text(bicycle.name, style = MaterialTheme.typography.titleMedium)
                            Text(bicycle.description, style = MaterialTheme.typography.bodySmall)
                            Text("Disponibilidad: Disponible", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}