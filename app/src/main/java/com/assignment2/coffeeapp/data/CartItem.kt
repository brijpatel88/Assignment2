package com.assignment2.coffeeapp.data

/**
 * Represents one item added to the cart
 * Includes all customizations selected by the user
 */
data class CartItem(
    val drink: Drink,

    // Size selection (Small / Medium / Large)
    val size: DrinkSize,

    // Customization options
    val milk: MilkType = MilkType.REGULAR,
    val sugar: SugarLevel = SugarLevel.ONE,
    val extras: List<Extra> = emptyList(),

    // Quantity
    val quantity: Int = 1
) {

    /**
     * Calculates total price for THIS cart item
     * Includes:
     * - base drink price
     * - size adjustment
     * - milk adjustment
     * - extras
     * - quantity
     */
    fun itemTotal(): Double {

        // Base drink price
        val base = drink.price

        // Size price
        val sizePrice = size.priceAdjustment

        // Milk price
        val milkPrice = milk.priceAdjustment

        // Extras total
        val extrasPrice = extras.sumOf { it.price }

        // Final price per single drink
        val singleItemTotal =
            base + sizePrice + milkPrice + extrasPrice

        // Multiply by quantity
        return singleItemTotal * quantity
    }
}