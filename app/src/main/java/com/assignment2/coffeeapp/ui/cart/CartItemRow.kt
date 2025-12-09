package com.assignment2.coffeeapp.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.assignment2.coffeeapp.data.CartItem
import com.assignment2.coffeeapp.data.CartManager

/**
 * A single row in the Cart screen.
 *
 * Shows:
 *  - Drink name + size
 *  - Quantity with - / + buttons
 *  - Remove button
 *  - Item total price
 *
 * NOTE:
 *  - We DO NOT keep local state for qty.
 *  - We always read quantity from the CartItem that comes from CartManager.
 *  - When we click - / + / Remove, we call CartManager and let the parent
 *    screen (CartScreen) recompose with the updated list.
 */
@Composable
fun CartItemRow(
    item: CartItem,
) {
    // ✅ Always read the latest quantity from the source of truth (CartManager)
    val qty = item.quantity

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        // ---------------------------------------
        // TOP: Name + Size
        // ---------------------------------------
        Text(
            text = item.drink.name,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Size: ${item.size.label}",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(Modifier.height(8.dp))

        // ---------------------------------------
        // MIDDLE: Quantity controls + Remove
        // ---------------------------------------
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            // Decrease quantity
            OutlinedButton(
                onClick = {
                    if (qty > 1) {
                        CartManager.updateQuantity(item, qty - 1)
                    }
                },
                modifier = Modifier.height(36.dp)
            ) {
                Text("-")
            }

            Spacer(Modifier.width(12.dp))

            // Current quantity
            Text(
                text = "Qty: $qty",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.width(12.dp))

            // Increase quantity
            OutlinedButton(
                onClick = {
                    CartManager.updateQuantity(item, qty + 1)
                },
                modifier = Modifier.height(36.dp)
            ) {
                Text("+")
            }

            Spacer(Modifier.width(16.dp))

            // Push "Remove" to the end of the row
            Spacer(modifier = Modifier.weight(1f))

            // Remove item button
            TextButton(
                onClick = { CartManager.removeItem(item) }
            ) {
                Text(
                    text = "Remove",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        // ---------------------------------------
        // BOTTOM: Item total price
        // ---------------------------------------
        Text(
            text = "Item total: $${"%.2f".format(item.itemTotal())}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}