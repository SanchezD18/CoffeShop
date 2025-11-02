package com.example.coffeshop

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeShopDetailScreen(selectedTitle: String) {
    val listaComentarios = remember {
        listOf(
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
    }
    
    val textSearch = rememberTextFieldState()
    var textOnSearch by rememberSaveable { mutableStateOf("") }
    val gridState = rememberLazyStaggeredGridState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = selectedTitle,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = AliviaRegularFont,
            fontSize = 30.sp,
            color = Color(0xFF000000)
        )
        
        DockedSearchBarConFiltro(
            textFieldState = textSearch,
            onSearch = { textOnSearch = it }
        )

        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            columns = StaggeredGridCells.Fixed(2),
            state = gridState,
        ) {
            items(listaComentarios.size) { indice ->
                val comentario = listaComentarios[indice]
                if (textOnSearch.isEmpty() || comentario.contains(textOnSearch, ignoreCase = true)) {
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
        
        val showButton by remember {
            derivedStateOf {
                gridState.firstVisibleItemIndex > 0
            }
        }

        val corutinesScope = rememberCoroutineScope()
        if (showButton) {
            Button(
                onClick = {corutinesScope.launch {
                    gridState.animateScrollToItem(0)
                }},
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)
            ) {
                Text(text = "Add new comment")
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DockedSearchBarConFiltro(
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(textFieldState.text.toString()) {
        onSearch(textFieldState.text.toString())
    }

    DockedSearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = textFieldState.text.toString(),
                onQueryChange = { textFieldState.edit { replace(0,
                    length, it) } },
                onSearch = {
                    onSearch(textFieldState.text.toString())
                    expanded = false
                },

                expanded = false,
                onExpandedChange = { expanded = false },
                placeholder = { Text("Search",
                    color = Color.Gray) },
                colors = SearchBarDefaults.inputFieldColors(
                    focusedTextColor = Color.Red
                )
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
    )
    {

    }
}