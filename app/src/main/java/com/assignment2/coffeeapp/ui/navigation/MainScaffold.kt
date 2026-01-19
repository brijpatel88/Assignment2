package com.assignment2.coffeeapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.NavDestination.Companion.hierarchy
import com.assignment2.coffeeapp.data.CartManager
import com.assignment2.coffeeapp.ui.bill.BillScreen
import com.assignment2.coffeeapp.ui.cart.CartScreen
import com.assignment2.coffeeapp.ui.details.DrinkDetailsScreen
import com.assignment2.coffeeapp.ui.drinks.DrinkListScreen
import com.assignment2.coffeeapp.ui.favorites.FavoritesScreen
import com.assignment2.coffeeapp.ui.history.OrderHistoryScreen
import com.assignment2.coffeeapp.ui.home.HomeScreen
import com.assignment2.coffeeapp.ui.payment.PaymentScreen
import com.assignment2.coffeeapp.ui.profile.ProfileScreen
import com.assignment2.coffeeapp.ui.review.ReviewOrderScreen
import com.assignment2.coffeeapp.ui.thankyou.ThankYouScreen

@Composable
fun MainScaffold(navController: NavHostController) {

    val bottomItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorites,
        BottomNavItem.Orders,
        BottomNavItem.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    // Show bottom bar ONLY on main tabs
    val mainTabRoutes = setOf(
        BottomNavItem.Home.route,
        BottomNavItem.Favorites.route,
        BottomNavItem.Orders.route,
        BottomNavItem.Profile.route
    )
    val showBottomBar = currentRoute in mainTabRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomItems.forEach { item ->
                        val selected = currentRoute == item.route

                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (currentRoute == item.route) return@NavigationBarItem

                                navController.navigate(item.route) {
                                    // ✅ Always go to the real tab route
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        inclusive = false
                                    }
                                    launchSingleTop = true

                                }
                            },
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) }
                        )
                    }
                }
            }
        },

        // Cart access WITHOUT adding it in bottom nav
        floatingActionButton = {
            if (showBottomBar) {
                val cartCount = CartManager.items.sumOf { it.quantity }

                FloatingActionButton(
                    onClick = {
                        navController.navigate("cart") {
                            launchSingleTop = true
                        }
                    }
                ) {
                    BadgedBox(
                        badge = {
                            if (cartCount > 0) {
                                Badge { Text(cartCount.toString()) }
                            }
                        }
                    ) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart")
                    }
                }
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier
                .padding(paddingValues)
                .navigationBarsPadding()
        ) {

            composable("home") {
                HomeScreen(
                    onCoffeeClick = { navController.navigate("drinks/COFFEE") },
                    onLatteClick = { navController.navigate("drinks/LATTE") },
                    onTeaClick = { navController.navigate("drinks/TEA") }
                )
            }

            composable("drinks/{category}") { backStackEntry ->
                val category = backStackEntry.arguments?.getString("category") ?: "COFFEE"
                DrinkListScreen(navController, category)
            }

            composable("details/{drinkId}") { backStackEntry ->
                val drinkId = backStackEntry.arguments?.getString("drinkId")?.toIntOrNull() ?: 1
                DrinkDetailsScreen(navController, drinkId)
            }

            // Cart flow
            composable("cart") { CartScreen(navController) }
            composable("review") { ReviewOrderScreen(navController) }
            composable("payment") { PaymentScreen(navController) }

            // Order-based screens
            composable("thankyou/{orderNumber}") { backStackEntry ->
                val orderNumber = backStackEntry.arguments?.getString("orderNumber")?.toIntOrNull() ?: 0
                ThankYouScreen(navController, orderNumber)
            }

            composable("bill/{orderNumber}") { backStackEntry ->
                val orderNumber = backStackEntry.arguments?.getString("orderNumber")?.toIntOrNull() ?: 0
                BillScreen(navController, orderNumber)
            }

            // Bottom tabs
            composable("favorites") { FavoritesScreen(navController) }
            composable("orders") { OrderHistoryScreen(navController) }
            composable("profile") { ProfileScreen(navController) }
        }
    }
}