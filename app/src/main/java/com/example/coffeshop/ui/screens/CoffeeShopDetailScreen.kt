package com.example.coffeshop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coffeshop.data.CoffeeShop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeShopDetailScreen(
    coffeeShop: CoffeeShop,
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = { Text(coffeeShop.titulo) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                }
            }
        )
        }
    Column() { RestaurantDetailCards() }
    }

@Composable
fun RestaurantDetailCards() {
    val comentarios = listOf(
        "Descubre el auténtico sabor del café de especialidad",
        "Ambiente perfecto para trabajar o conversar",
        "Café recién tostado por baristas certificados",
        "Prueba nuestros postres artesanales",
        "Especialistas en métodos Chemex y V60",
        "Diseño industrial con toques cálidos",
        "Espacio pet-friendly en nuestra terraza",
        "Zona de trabajo con enchufes y WiFi",
        "Ubicación céntrica con fácil acceso",
        "Servicio de reservas para grupos",
        "Take away con empaques sustentables",
        "Granos de café de origen único",
        "Menú de brunch los fines de semana",
        "Opciones vegetarianas y veganas",
        "Horario extendido hasta medianoche",
        "Noches de jazz en vivo los viernes",
        "Talleres de barista mensuales",
        "Programa de lealtad: 10ª bebida gratis"
    )

    LazyColumn() {
        items(comentarios) { comentario ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Text(
                    text = comentario,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}