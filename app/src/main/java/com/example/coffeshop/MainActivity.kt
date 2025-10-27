package com.example.coffeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffeshop.data.CoffeeShop
import com.example.coffeshop.ui.screens.CoffeeShopDetailScreen
import com.example.coffeshop.ui.screens.CoffeeShopListScreen
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
    var selectedCoffeeShop by remember { mutableStateOf<CoffeeShop?>(null) }
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                "coffee_shops" -> {
                    CoffeeShopListScreen(
                        onCoffeeShopClick = { coffeeShop ->
                            selectedCoffeeShop = coffeeShop
                            currentScreen = "coffee_shop_detail"
                        },
                        onShareClick = {
                        },
                        onAlbumClick = {
                        }
                    )
                }
                "coffee_shop_detail" -> {
                    selectedCoffeeShop?.let { coffeeShop ->
                        CoffeeShopDetailScreen(
                            coffeeShop = coffeeShop,
                            onBackClick = { currentScreen = "coffee_shops" }
                        )
                    }
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