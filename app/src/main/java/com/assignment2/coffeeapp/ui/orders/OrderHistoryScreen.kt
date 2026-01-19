package com.assignment2.coffeeapp.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.assignment2.coffeeapp.data.*
import java.util.Locale

@Composable
fun OrderHistoryScreen(navController: NavController) {

    val orders = OrderHistoryManager.orders

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 80.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.tertiary
                        )
                    ),
                    shape = RoundedCornerShape(22.dp)
                )
                .padding(18.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.BottomStart)) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.ReceiptLong, contentDescription = null, tint = Color.White)
                    Spacer(Modifier.width(8.dp))
                    Text("Order History", style = MaterialTheme.typography.titleLarge, color = Color.White)
                }

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "Your past orders will appear here.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.92f)
                )
            }
        }

        Spacer(Modifier.height(14.dp))

        if (orders.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("No past orders yet ☕", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Place an order and it will show up here.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(items = orders, key = { it.orderNumber }) { order ->
                    OrderCard(
                        order = order,
                        onViewBill = { navController.navigate("bill/${order.orderNumber}") },
                        onReorder = {
                            // ✅ put items back into cart
                            order.lines.forEach { line ->
                                val drink = DrinkDataProvider.getById(line.drinkId) ?: return@forEach

                                val size = DrinkSize.values().firstOrNull { it.label == line.sizeLabel } ?: DrinkSize.SMALL
                                val milk = MilkType.values().firstOrNull { it.label == line.milkLabel } ?: MilkType.REGULAR
                                val sugar = SugarLevel.values().firstOrNull { it.label == line.sugarLabel } ?: SugarLevel.ONE
                                val extras = Extra.values().filter { e -> line.extras.contains(e.label) }

                                repeat(line.quantity) {
                                    CartManager.addToCart(
                                        CartItem(
                                            drink = drink,
                                            size = size,
                                            milk = milk,
                                            sugar = sugar,
                                            extras = extras
                                        )
                                    )
                                }
                            }

                            navController.navigate("cart")
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun OrderCard(
    order: Order,
    onViewBill: () -> Unit,
    onReorder: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Order #${order.orderNumber}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                AssistChip(onClick = {}, label = { Text("$${"%.2f".format(order.totalAmount)}") })
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = order.dateTime,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = order.itemsSummary,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedButton(onClick = onViewBill, modifier = Modifier.weight(1f)) {
                    Text("View Bill")
                }
                Button(onClick = onReorder, modifier = Modifier.weight(1f)) {
                    Text("Re-order")
                }
            }
        }
    }
}