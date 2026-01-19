package com.assignment2.coffeeapp.data

import androidx.compose.runtime.mutableStateListOf

/**
 * Central cart state holder
 * Uses Compose-friendly state list
 */
object CartManager {

    // 🔥 Reactive cart list (Compose observes this automatically)
    private val _items = mutableStateListOf<CartItem>()

    // Read-only access for UI
    val items: List<CartItem>
        get() = _items

    /**
     * ADD item to cart
     */
    fun addToCart(item: CartItem) {
        _items.add(item)
    }

    /**
     * UPDATE quantity of an item
     * Replaces item instead of mutating (important for Compose)
     */
    fun updateQuantity(item: CartItem, newQty: Int) {
        val index = _items.indexOf(item)

        if (index != -1 && newQty > 0) {
            _items[index] = item.copy(quantity = newQty)
        }
    }

    /**
     * REMOVE item from cart
     */
    fun removeItem(item: CartItem) {
        _items.remove(item)
    }

    /**
     * CLEAR entire cart
     */
    fun clearCart() {
        _items.clear()
    }

    /**
     * SUBTOTAL (before tax)
     */
    fun getSubtotal(): Double {
        return _items.sumOf { it.itemTotal() }
    }

    /**
     * TAX calculation (14%)
     */
    fun getTax(): Double {
        return getSubtotal() * 0.14
    }

    /**
     * FINAL TOTAL
     */
    fun getTotal(): Double {
        return getSubtotal() + getTax()
    }
}