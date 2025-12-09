package com.assignment2.coffeeapp.data

data class CartItem(
    val drink: Drink,
    val size: DrinkSize,
    val quantity: Int = 1
) {
    fun itemTotal(): Double {
        return (drink.price + size.priceAdjustment) * quantity
    }
}