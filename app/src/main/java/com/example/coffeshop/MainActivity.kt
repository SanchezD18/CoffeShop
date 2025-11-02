package com.example.coffeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffeshop.ui.theme.CoffeShopTheme

val AliviaRegularFont = FontFamily(Font(R.font.aliviaregular))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeShopTheme {
                CoffeeShopApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeShopApp() {
    var currentScreen by remember { mutableStateOf("coffee_shops") }
    var selectedTitle by remember { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    if (currentScreen == "coffee_shops") {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                    } else {
                        IconButton(onClick = {
                            currentScreen = "coffee_shops"
                        }) {
                            Icon(Icons.Default.Close, contentDescription = "Volver")
                        }
                    }
                },
                title = {
                    Text(
                        text = "CoffeShops",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors= TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                actions = {
                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = "Opciones",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Compartir") },
                                onClick = {
                                    expanded = false
                                },
                                leadingIcon = {
                                    Icon(Icons.Default.Share, contentDescription = null)
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Álbum") },
                                onClick = {
                                    expanded = false
                                },
                                leadingIcon = {
                                    Icon(Icons.Default.Lock, contentDescription = null)
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                "coffee_shops" -> {
                    CoffeeShopListScreen(
                        onNavigate = { title ->
                            selectedTitle = title
                            currentScreen = "coffee_shop_detail"
                        }
                    )
                }
                "coffee_shop_detail" -> {
                    CoffeeShopDetailScreen(selectedTitle?: "CoffeShops")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoffeeShopAppPreview() {
    CoffeShopTheme {
        CoffeeShopApp()
    }
}