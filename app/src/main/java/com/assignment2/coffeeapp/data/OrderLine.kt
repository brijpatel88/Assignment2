package com.assignment2.coffeeapp.data

data class OrderLine(
    val drinkId: Int,
    val drinkName: String,
    val sizeLabel: String,
    val milkLabel: String,
    val sugarLabel: String,
    val extras: List<String>,
    val quantity: Int,
    val lineTotal: Double
)