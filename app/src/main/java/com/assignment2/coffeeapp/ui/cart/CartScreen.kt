package com.assignment2.coffeeapp.ui.cart

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
 * -----------------------------------------------------------
 *  CART SCREEN
 *  Shows all items currently inside the cart.
 *  Allows user to:
 *      - View list of items
 *      - Update quantities (inside CartItemRow)
 *      - Remove items (inside CartItemRow)
 *      - See subtotal, tax, total
 *      - Navigate to Review screen
 * -----------------------------------------------------------
 */
@Composable
fun CartScreen(navController: NavController) {

    // -----------------------------------------------------------
    // 1) Observe the reactive cart items
    // CartManager.items is a mutableStateList, so UI updates automatically
    // whenever the list changes (add, remove, update quantity)
    // -----------------------------------------------------------
    val items = CartManager.items

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // -----------------------------------------------------------
        // Title
        // -----------------------------------------------------------
        Text(
            text = "Your Cart",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        // -----------------------------------------------------------
        // 2) When cart is empty
        // -----------------------------------------------------------
        if (items.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Your cart is empty.")
            }

        } else {

            // -----------------------------------------------------------
            // 3) Show list of cart items
            // LazyColumn displays each cart item using CartItemRow
            // The 'key' ensures animations and stability
            // -----------------------------------------------------------
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = items,
                    key = { it.hashCode() }    // Unique key per item
                ) { item ->

                    CartItemRow(item)   // Single row per cart item
                    Divider()
                }
            }

            Spacer(Modifier.height(16.dp))

            // -----------------------------------------------------------
            // 4) Price summary
            // -----------------------------------------------------------
            Text("Subtotal: $${"%.2f".format(CartManager.getSubtotal())}")
            Text("Tax (14%): $${"%.2f".format(CartManager.getTax())}")
            Text("Total: $${"%.2f".format(CartManager.getTotal())}")

            Spacer(Modifier.height(16.dp))

            // -----------------------------------------------------------
            // 5) Continue shopping button
            // Takes user back to HomeScreen (categories)
            // -----------------------------------------------------------
            Button(
                onClick = {
                    navController.navigate("home")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add more drinks")
            }

            Spacer(Modifier.height(8.dp))

            // -----------------------------------------------------------
            // 6) Checkout button
            // Moves user to ReviewOrderScreen
            // -----------------------------------------------------------
            Button(
                onClick = {
                    navController.navigate("review")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Checkout")
            }
        }
    }
}