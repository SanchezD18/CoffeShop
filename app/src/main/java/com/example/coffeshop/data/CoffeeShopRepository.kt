package com.example.coffeshop.data

import com.example.coffeshop.R

object CoffeeShopRepository {
    fun getCoffeeShops(): List<CoffeeShop> = listOf(
        CoffeeShop(
            id = 1,
            titulo = "Antico Caffè Greco",
            subtit = "St. Italy, Rome",
            imageRes = R.drawable.images,
            description = "El café más antiguo de Roma, fundado en 1760. Un lugar histórico donde se reunían artistas y escritores.",
            rating = 4.8f,
            isOpen = true
        ),
        CoffeeShop(
            id = 2,
            titulo = "Coffee Room",
            subtit = "St. Germany, Berlin",
            imageRes = R.drawable.images1,
            description = "Café moderno en el corazón de Berlín con ambiente acogedor y excelente café de especialidad.",
            rating = 4.5f,
            isOpen = true
        ),
        CoffeeShop(
            id = 3,
            titulo = "Coffee Ibiza",
            subtit = "St. Colon, Madrid",
            imageRes = R.drawable.images2,
            description = "Café con ambiente mediterráneo en el centro de Madrid. Perfecto para desayunos y meriendas.",
            rating = 4.3f,
            isOpen = true
        ),
        CoffeeShop(
            id = 4,
            titulo = "Pudding Coffee Shop",
            subtit = "St. Diagonal, Barcelona",
            imageRes = R.drawable.images3,
            description = "Café especializado en postres y café artesanal. Ubicado en la famosa Diagonal de Barcelona.",
            rating = 4.6f,
            isOpen = true
        ),
        CoffeeShop(
            id = 5,
            titulo = "L'Express",
            subtit = "St. Picadilly Circus, London",
            imageRes = R.drawable.images4,
            description = "Café parisino en el corazón de Londres. Ambiente elegante y café francés auténtico.",
            rating = 4.4f,
            isOpen = true
        ),
        CoffeeShop(
            id = 6,
            titulo = "Coffee Corner",
            subtit = "St. Àngel Guimerà, Valencia",
            imageRes = R.drawable.images5,
            description = "Rincón acogedor en Valencia con café de origen y ambiente familiar.",
            rating = 4.7f,
            isOpen = true
        ),
        CoffeeShop(
            id = 7,
            titulo = "Sweet Cup",
            subtit = "St.Kinkerstraat, Amsterdam",
            imageRes = R.drawable.images6,
            description = "Café holandés con especialidad en postres y café de calidad. Ambiente relajado y moderno.",
            rating = 4.2f,
            isOpen = true
        )
    )
}

