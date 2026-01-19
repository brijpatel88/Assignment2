package com.assignment2.coffeeapp.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.assignment2.coffeeapp.data.CartManager

@Composable
fun CartScreen(navController: NavController) {

    val items = CartManager.items

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 80.dp)
    ) {

        Text(
            text = "Your Cart",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(12.dp))

        // ✅ EMPTY CART UI (instead of showing an empty list)
        if (items.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Your cart is empty 🛒", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Add some drinks to continue.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(Modifier.height(14.dp))

                    Button(
                        onClick = { navController.navigate("home") },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Go to Home")
                    }
                }
            }

            return // ✅ stop here (don’t draw totals/buttons)
        }

        // ✅ Cart list
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = items,
                key = { it.hashCode() }
            ) { item ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        CartItemRow(item)
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // ✅ Price summary
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text("Subtotal: $${"%.2f".format(CartManager.getSubtotal())}")
                Text("Tax (14%): $${"%.2f".format(CartManager.getTax())}")
                Divider(modifier = Modifier.padding(vertical = 10.dp))
                Text(
                    "Total: $${"%.2f".format(CartManager.getTotal())}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // ✅ Actions
        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add more drinks")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { navController.navigate("review") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Checkout")
        }
    }
}