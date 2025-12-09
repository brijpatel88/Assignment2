package com.assignment2.coffeeapp.ui.review

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.assignment2.coffeeapp.data.CartManager

/**
 * ---------------------------------------------------------
 * REVIEW ORDER SCREEN
 * Shows: all cart items + subtotal/tax/total
 * ---------------------------------------------------------
 */
@Composable
fun ReviewOrderScreen(navController: NavController) {

    // 🔥 Use updated CartManager
    val items = CartManager.items
    val subtotal = CartManager.getSubtotal()
    val tax = CartManager.getTax()
    val total = CartManager.getTotal()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Review Your Order", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        // ---------------------------------------------------------
        // LIST OF CART ITEMS
        // ---------------------------------------------------------
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items) { item ->

                Column(Modifier.padding(vertical = 8.dp)) {

                    // Drink name + size
                    Text(
                        "${item.drink.name} (${item.size.label})",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    // Quantity
                    Text("Qty: ${item.quantity}")

                    // Price per item
                    Text("Item total: $${"%.2f".format(item.itemTotal())}")
                }

                Divider()
            }
        }

        Spacer(Modifier.height(16.dp))

        // ---------------------------------------------------------
        // ORDER SUMMARY
        // ---------------------------------------------------------
        Text("Subtotal: $${"%.2f".format(subtotal)}")
        Text("Tax: $${"%.2f".format(tax)}")
        Text("Total: $${"%.2f".format(total)}")

        Spacer(Modifier.height(24.dp))

        // ---------------------------------------------------------
        // PAYMENT BUTTON
        // ---------------------------------------------------------
        Button(
            onClick = { navController.navigate("payment") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Proceed to Payment")
        }

        Spacer(Modifier.height(12.dp))

        // ---------------------------------------------------------
        // BACK BUTTON
        // ---------------------------------------------------------
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Cart")
        }
    }
}