package com.example.coffeshop.ui.screens

import android.os.strictmode.CredentialProtectedWhileLockedViolation
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    onCoffeeShopClick: (CoffeeShop) -> Unit = {}
) {
    val coffeeShops = remember { CoffeeShopRepository.getCoffeeShops() }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeShopCard(
    coffeeShop: CoffeeShop,
    onClick: () -> Unit
) {
    var rating by remember { mutableIntStateOf(0) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Column (
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Top
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
                    overflow = TextOverflow.Ellipsis,
                    color = Color(0xFF000000)
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
                        fontSize = 20.sp,)
                }

            }
        }
    }
