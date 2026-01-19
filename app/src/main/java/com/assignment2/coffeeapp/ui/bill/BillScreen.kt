package com.assignment2.coffeeapp.ui.bill

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.assignment2.coffeeapp.data.OrderHistoryManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillScreen(
    navController: NavController,
    orderNumber: Int
) {
    val order = OrderHistoryManager.getByOrderNumber(orderNumber)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bill / Receipt") },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) { Text("Back") }
                }
            )
        }
    ) { padding ->

        if (order == null) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Bill not found.", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(12.dp))
                Button(onClick = { navController.popBackStack() }) { Text("Go Back") }
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
                .padding(bottom = 80.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("CoffeeApp Receipt", style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(6.dp))
                    Text("Order #${order.orderNumber}", style = MaterialTheme.typography.bodyLarge)
                    Text(order.dateTime, style = MaterialTheme.typography.bodySmall)
                }
            }

            Spacer(Modifier.height(14.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(order.lines) { line ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text("${line.quantity}× ${line.drinkName}", style = MaterialTheme.typography.titleMedium)
                            Text("Size: ${line.sizeLabel} • Milk: ${line.milkLabel} • Sugar: ${line.sugarLabel}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            if (line.extras.isNotEmpty()) {
                                Text(
                                    "Extras: ${line.extras.joinToString()}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                            Text(
                                "Line total: $${"%.2f".format(line.lineTotal)}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            Divider()

            Spacer(Modifier.height(10.dp))

            Text(
                "Total Paid: $${"%.2f".format(order.totalAmount)}",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}