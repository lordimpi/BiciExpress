package edu.unicauca.apimovil.biciexpress

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import edu.unicauca.apimovil.biciexpress.model.Bicycle
import edu.unicauca.apimovil.biciexpress.viewmodel.AuthViewModel
import edu.unicauca.apimovil.biciexpress.viewmodel.CatalogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    navController: NavController,
    catalogViewModel: CatalogViewModel = viewModel(),
    authViewModel: AuthViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Catálogo de Bicicletas") })
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            CatalogMapScreen(bicycles = catalogViewModel.bicycles)
            Spacer(modifier = Modifier.height(16.dp))

            when {
                catalogViewModel.isLoading -> {
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                catalogViewModel.errorMessage.isNotBlank() -> {
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Text(catalogViewModel.errorMessage, color = MaterialTheme.colorScheme.error)
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f) // ✅ Solo la lista es scrollable
                            .padding(horizontal = 16.dp)
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
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = bicycle.imageUrl,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(80.dp)
                                            .padding(end = 12.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                    )

                                    Column {
                                        Text(bicycle.name, style = MaterialTheme.typography.titleMedium)
                                        Text(bicycle.description, style = MaterialTheme.typography.bodySmall)
                                        Text("Tipo: ${bicycle.type}", style = MaterialTheme.typography.bodySmall)
                                        Text("Precio: $${bicycle.pricePerHour} /hora", style = MaterialTheme.typography.bodySmall)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CatalogMapScreen(bicycles: List<Bicycle>) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(2.4448, -76.6147), 13f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp) // ✅ Altura fija
            .clip(RoundedCornerShape(12.dp)),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = true,
            scrollGesturesEnabled = true,
            zoomGesturesEnabled = true
        )
    ) {
        bicycles.forEach { bike ->
            Marker(
                state = MarkerState(
                    position = LatLng(bike.latitude, bike.longitude)
                ),
                title = bike.name,
                snippet = bike.description
            )
        }
    }
}