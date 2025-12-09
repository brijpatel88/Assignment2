package com.assignment2.coffeeapp.ui.thankyou

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.assignment2.coffeeapp.data.CartManager

@Composable
fun ThankYouScreen(navController: NavController) {

    // -----------------------------------------
    // FINAL SCREEN AFTER PAYMENT SUCCESSFUL
    // -----------------------------------------
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Thank you message
        Text(
            "Thank you for your order!",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(20.dp))

        // START NEW ORDER BUTTON
        Button(
            onClick = {

                // Ensure cart is empty
                CartManager.clearCart()

                // Navigate back to Intro screen
                navController.navigate("intro") {
                    // Clear everything from back stack so user cannot return
                    popUpTo("intro") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start New Order")
        }
    }
}