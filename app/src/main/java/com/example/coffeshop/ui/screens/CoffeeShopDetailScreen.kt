package com.example.coffeshop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeshop.AliviaRegularFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeShopDetailScreen(selectedTitle: String) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    var isSearching by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
    }
    Column(Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = selectedTitle,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = AliviaRegularFont,
            fontSize = 30.sp,
            color = Color(0xFF000000)
        )
        DockedSearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearch = {
                isSearching = false
                isSearchActive = false
            },
            active = isSearchActive,
            onActiveChange = { isSearchActive = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            if (searchQuery.isNotEmpty()) {
                Text(
                    text = "Buscar: $searchQuery",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        RestaurantDetailCards(
            searchQuery = searchQuery,
            isSearching = isSearching,
            onClearSearch = {
                searchQuery = ""
                isSearching = true
            })
    }
}

@Composable
fun RestaurantDetailCards(
    searchQuery: String = "",
    isSearching: Boolean = false,
    onClearSearch: () -> Unit = {}
) {
    val gridState = rememberLazyStaggeredGridState()
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
Column() {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxSize()
            .weight(1f),
        columns = StaggeredGridCells.Fixed(2),
        state = gridState,
    ){
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

    val showButton by remember{
        derivedStateOf {
            gridState.firstVisibleItemIndex > 0
        }
    }
    if(showButton) {
        Button(
            onClick = {},
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)
        ) {
            Text(text = "Add new comment")
        }
    }
}
}
