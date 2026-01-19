package com.assignment2.coffeeapp.data

/**
 * Milk options for a drink
 * Each option may increase the base price
 */
enum class MilkType(
    val label: String,
    val priceAdjustment: Double
) {
    REGULAR("Regular Milk", 0.0),
    ALMOND("Almond Milk", 0.50),
    OAT("Oat Milk", 0.75),
    SOY("Soy Milk", 0.50)
}