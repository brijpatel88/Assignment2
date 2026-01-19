package com.assignment2.coffeeapp.ui.review

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.assignment2.coffeeapp.data.CartItem
import com.assignment2.coffeeapp.data.CartManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewOrderScreen(navController: NavController) {

    val items = CartManager.items

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Review Order") },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text("Back")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
                .padding(bottom = 80.dp)
        ) {

            // ✅ Header card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Almost done ☕",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Please review your items before payment.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(Modifier.height(14.dp))

            // ✅ Items list
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = items,
                    key = { "${it.drink.id}-${it.size}-${it.milk}-${it.sugar}-${it.extras.joinToString { e -> e.name }}" }
                ) { item ->
                    ReviewItemCard(item)
                }
            }

            Spacer(Modifier.height(14.dp))

            // ✅ Summary card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    SummaryRow(label = "Subtotal", value = CartManager.getSubtotal())
                    SummaryRow(label = "Tax (14%)", value = CartManager.getTax())
                    Divider(modifier = Modifier.padding(vertical = 10.dp))
                    SummaryRow(
                        label = "Total",
                        value = CartManager.getTotal(),
                        isTotal = true
                    )
                }
            }

            Spacer(Modifier.height(14.dp))

            // ✅ Pay button
            Button(
                onClick = { navController.navigate("payment") },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Text("Proceed to Payment")
            }
        }
    }
}

@Composable
private fun ReviewItemCard(item: CartItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {

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

                Text(
                    text = "$${"%.2f".format(item.itemTotal())}",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Qty: ${item.quantity}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SummaryRow(
    label: String,
    value: Double,
    isTotal: Boolean = false
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "$${"%.2f".format(value)}",
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium
        )
    }
}