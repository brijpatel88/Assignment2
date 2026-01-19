package com.assignment2.coffeeapp.data

/**
 * Optional extras (multiple can be selected)
 */
enum class Extra(
    val label: String,
    val price: Double
) {
    WHIPPED_CREAM("Whipped Cream", 0.75),
    CARAMEL("Caramel Syrup", 0.50),
    CHOCOLATE("Chocolate Syrup", 0.50),
    EXTRA_SHOT("Extra Espresso Shot", 1.00)
}