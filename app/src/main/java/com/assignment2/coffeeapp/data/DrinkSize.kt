package com.assignment2.coffeeapp.data

/**
 * Drink sizes. Each size increases the base price.
 */
enum class DrinkSize(
    val label: String,
    val priceAdjustment: Double
) {
    SMALL("Small", 0.0),
    MEDIUM("Medium", 0.50),
    LARGE("Large", 1.00)
}