package com.assignment2.coffeeapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem("home", "Home", Icons.Filled.Home)
    object Favorites : BottomNavItem("favorites", "Favorites", Icons.Filled.Favorite)
    object Orders : BottomNavItem("orders", "Orders", Icons.Filled.ReceiptLong)
    object Profile : BottomNavItem("profile", "Profile", Icons.Filled.Person)
}