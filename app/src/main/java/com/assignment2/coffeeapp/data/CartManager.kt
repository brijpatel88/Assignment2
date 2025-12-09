package com.assignment2.coffeeapp.data

import androidx.compose.runtime.mutableStateListOf

/**
 * CartManager
 * Manages all cart CRUD operations and live totals.
 * Uses Compose state so the UI auto-updates.
 */
object CartManager {

    // Reactive list — UI updates automatically
    private val _items = mutableStateListOf<CartItem>()

    // Public read-only list
    val items: List<CartItem>
        get() = _items

    // CREATE — Add an item to the cart
    fun addToCart(item: CartItem) {
        _items.add(item)
    }

    // UPDATE — Change quantity of an item
    fun updateQuantity(item: CartItem, qty: Int) {
        val index = _items.indexOf(item)
        if (index != -1) {
            _items[index] = _items[index].copy(quantity = qty)
        }
    }

    // DELETE — Remove item
    fun removeItem(item: CartItem) {
        _items.remove(item)
    }

    // CLEAR — Empty the whole cart
    fun clearCart() = _items.clear()

    // SUBTOTAL
    fun getSubtotal(): Double =
        _items.sumOf { it.itemTotal() }

    // TAX (14%)
    fun getTax(): Double =
        getSubtotal() * 0.14

    // TOTAL = subtotal + tax
    fun getTotal(): Double =
        getSubtotal() + getTax()
}