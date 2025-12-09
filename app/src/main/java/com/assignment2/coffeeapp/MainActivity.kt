package com.assignment2.coffeeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assignment2.coffeeapp.ui.cart.CartScreen
import com.assignment2.coffeeapp.ui.details.DrinkDetailsScreen
import com.assignment2.coffeeapp.ui.drinks.DrinkListScreen
import com.assignment2.coffeeapp.ui.home.HomeScreen
import com.assignment2.coffeeapp.ui.intro.IntroScreen
import com.assignment2.coffeeapp.ui.thankyou.ThankYouScreen
import com.assignment2.coffeeapp.ui.theme.CoffeeAppTheme
import com.assignment2.coffeeapp.ui.review.ReviewOrderScreen
import com.assignment2.coffeeapp.ui.payment.PaymentScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeAppTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "intro"
                    ) {
                        composable("intro") {
                            IntroScreen(navController)
                        }

                        composable("home") {
                            HomeScreen(navController)
                        }

                        composable("drinks/{category}") { backStackEntry ->
                            val raw = backStackEntry.arguments?.getString("category") ?: "COFFEE"
                            val category = raw.uppercase()
                            DrinkListScreen(navController, category)
                        }

                        composable("details/{drinkId}") { backStackEntry ->
                            val drinkId =
                                backStackEntry.arguments?.getString("drinkId")?.toIntOrNull() ?: 1
                            DrinkDetailsScreen(navController, drinkId)
                        }

                        composable("cart") {
                            CartScreen(navController)
                        }

                        // Review order screen
                        composable("review") {
                            ReviewOrderScreen(navController)
                        }

                        //  Payment screen
                        composable("payment") {
                            PaymentScreen(navController)
                        }

                        composable("thankyou") {
                            ThankYouScreen(navController)
                        }
                    }
                }
            }
        }
    }
}