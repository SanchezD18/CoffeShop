package com.example.coffeshop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeshop.AliviaRegularFont
import com.example.coffeshop.data.CoffeeShop
import com.example.coffeshop.data.CoffeeShopRepository
import com.example.coffeshop.ui.components.RatingBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeShopListScreen(
    onCoffeeShopClick: (CoffeeShop) -> Unit = {},
    onShareClick: () -> Unit = {},
    onAlbumClick: () -> Unit = {}
) {
    val coffeeShops = remember { CoffeeShopRepository.getCoffeeShops() }
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cafeterías",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "Opciones",
                        tint = MaterialTheme.colorScheme.primary
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
                            onShareClick()
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Share, contentDescription = null)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Álbum") },
                        onClick = {
                            expanded = false
                            onAlbumClick()
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null)
                        }
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(coffeeShops) { coffeeShop ->
                CoffeeShopCard(
                    coffeeShop = coffeeShop,
                    onClick = { onCoffeeShopClick(coffeeShop) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeShopCard(
    coffeeShop: CoffeeShop,
    onClick: () -> Unit
) {
    var rating by remember { mutableIntStateOf(0) }
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
    ) {
        Column (
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = coffeeShop.imageRes),
                contentDescription = coffeeShop.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = coffeeShop.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontFamily = AliviaRegularFont,
                    fontSize = 32.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row(verticalAlignment = Alignment.CenterVertically){
                    RatingBar(
                        rating = rating,
                        modifier = Modifier.padding(vertical = 4.dp),
                        onRatingChanged = { newRating ->
                            rating = newRating
                        }
                    )
                }

                Text(
                    text = coffeeShop.subtit,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 20.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(start = 0.dp, end = 100.dp, top = 10.dp)
                        .width(250.dp)
                )
            Spacer(modifier = Modifier.height(5.dp))
                HorizontalDivider()
                TextButton(modifier = Modifier.padding(start = 0.dp, end = 240.dp, top = 2.dp),

                    onClick = {}) {
                    Text(text = "Reserve",
                        fontSize = 28.sp,)
                }

            }
        }
    }
