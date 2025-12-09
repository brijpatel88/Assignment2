package com.assignment2.coffeeapp.data

data class Drink(
    val id: Int,
    val name: String,
    val image: Int,         // drawable resource ID
    val shortDesc: String,
    val price: Double,
    val type: DrinkType,
    val sizes: List<DrinkSize>
)