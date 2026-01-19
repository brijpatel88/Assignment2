package com.assignment2.coffeeapp.data

import androidx.compose.runtime.mutableStateListOf

/**
 * Stores favorite drink IDs in memory (assignment-friendly).
 * Uses a reactive list so UI updates instantly.
 */
object FavoritesManager {

    private val _favoriteIds = mutableStateListOf<Int>()
    val favoriteIds: List<Int> get() = _favoriteIds

    fun isFavorite(drinkId: Int): Boolean = _favoriteIds.contains(drinkId)

    fun toggle(drinkId: Int) {
        if (_favoriteIds.contains(drinkId)) _favoriteIds.remove(drinkId)
        else _favoriteIds.add(0, drinkId) // newest first
    }

    fun clear() {
        _favoriteIds.clear()
    }
}