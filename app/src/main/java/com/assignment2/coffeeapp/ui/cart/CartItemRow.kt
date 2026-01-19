package com.assignment2.coffeeapp.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.assignment2.coffeeapp.data.CartItem
import com.assignment2.coffeeapp.data.CartManager

@Composable
fun CartItemRow(item: CartItem) {

    val qty = item.quantity

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {

            // शीर्ष row: name + total
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.drink.name,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(Modifier.height(4.dp))

                    // Compact customization line
                    Text(
                        text = "${item.size.label} • ${item.milk.label} • Sugar: ${item.sugar.label}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    if (item.extras.isNotEmpty()) {
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = "Extras: ${item.extras.joinToString { it.label }}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Spacer(Modifier.width(10.dp))

                Text(
                    text = "$${"%.2f".format(item.itemTotal())}",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(Modifier.height(12.dp))

            // qty controls + remove
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = {
                        if (qty > 1) CartManager.updateQuantity(item, qty - 1)
                    },
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                ) { Text("-") }

                Spacer(Modifier.width(12.dp))

                Text("Qty: $qty", style = MaterialTheme.typography.bodyMedium)

                Spacer(Modifier.width(12.dp))

                OutlinedButton(
                    onClick = { CartManager.updateQuantity(item, qty + 1) },
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                ) { Text("+") }

                Spacer(Modifier.weight(1f))

                TextButton(onClick = { CartManager.removeItem(item) }) {
                    Text("Remove", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}