package com.example.coffeshop.data

data class CoffeeShop(
    val id: Int,
    val titulo: String,
    val subtit: String,
    val imageRes: Int,
    val description: String = "",
    val rating: Float = 0f,
    val isOpen: Boolean = true
)
