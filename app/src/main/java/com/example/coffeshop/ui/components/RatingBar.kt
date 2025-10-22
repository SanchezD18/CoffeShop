package com.example.coffeshop.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    modifier:Modifier=Modifier,
    rating:Int=0,
    stars:Int=5,
    starsColor:Color=Color.Red,
    onRatingChanged: (Int) -> Unit = {}
){
    Row(modifier=modifier){
        for (i in 1..stars) {
            val icon = if (i <= rating) {
                Icons.Filled.Favorite
            } else {
                Icons.Filled.FavoriteBorder
            }
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier
                    .clickable {
                        onRatingChanged(i)
                    })
        }
    }
}

@Composable
fun InteractiveRatingBar(
    rating: Float,
    maxRating: Int = 5,
    onRatingChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentRating by remember { mutableFloatStateOf(rating) }
    
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        repeat(maxRating) { index ->
            val starRating = index + 1
            val isFilled = starRating <= currentRating
            
            IconButton(
                onClick = { 
                    currentRating = starRating.toFloat()
                    onRatingChange(currentRating)
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = if (isFilled) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Estrella $starRating",
                    tint = if (isFilled) 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
