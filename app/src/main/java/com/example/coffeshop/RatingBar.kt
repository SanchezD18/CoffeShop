package com.example.coffeshop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
                    .size(40.dp)
                    .clickable {
                        onRatingChanged(i)
                    })
        }
    }
}
