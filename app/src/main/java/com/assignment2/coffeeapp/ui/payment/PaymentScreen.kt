package com.assignment2.coffeeapp.ui.payment

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
fun PaymentScreen(navController: NavController) {

    // ---------------------------------------
    // PAYMENT SCREEN (Simple Assignment Style)
    // ---------------------------------------
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Title
        Text(
            "Payment",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(20.dp))

        // Total Price
        Text(
            "Total: $${"%.2f".format(CartManager.getTotal())}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(40.dp))

        // ---------------------------------------
        // PAY NOW BUTTON
        // ---------------------------------------
        Button(
            onClick = {

                // Clear the cart after successful payment
                CartManager.clearCart()

                // Navigate to Thank You screen AND clear back stack
                navController.navigate("thankyou") {
                    popUpTo("home") { inclusive = false }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pay Now")
        }
    }
}