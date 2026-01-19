package com.assignment2.coffeeapp.ui.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.assignment2.coffeeapp.data.CartManager
import com.assignment2.coffeeapp.data.Order
import com.assignment2.coffeeapp.data.OrderHistoryManager
import com.assignment2.coffeeapp.data.OrderLine
import com.assignment2.coffeeapp.viewmodel.PaymentViewModel
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.rememberPaymentSheet
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController) {

    val vm: PaymentViewModel = viewModel()

    val subtotal = CartManager.getSubtotal()
    val tax = CartManager.getTax()
    val totalAmountDollars = CartManager.getTotal()
    val amountInCents = remember(totalAmountDollars) { (totalAmountDollars * 100).toInt() }

    val isLoading by vm.isLoading.collectAsState()
    val error by vm.error.collectAsState()

    // ✅ Compose-safe Stripe PaymentSheet
    val paymentSheet = rememberPaymentSheet { result ->
        when (result) {
            is PaymentSheetResult.Completed -> {

                // ✅ snapshot cart items BEFORE clearing
                val cartSnapshot = CartManager.items.toList()

                val itemsSummary = if (cartSnapshot.isEmpty()) {
                    "No items"
                } else {
                    cartSnapshot.joinToString(separator = ", ") { item ->
                        "${item.quantity}× ${item.drink.name} (${item.size.label})"
                    }
                }

                val orderNumber = (100000..999999).random()
                val dateTime = SimpleDateFormat(
                    "MMM dd, yyyy • hh:mm a",
                    Locale.getDefault()
                ).format(Date())

                // ✅ Full lines for Bill + Reorder
                val lines = cartSnapshot.map { item ->
                    OrderLine(
                        drinkId = item.drink.id,
                        drinkName = item.drink.name,
                        sizeLabel = item.size.label,
                        milkLabel = item.milk.label,
                        sugarLabel = item.sugar.label,
                        extras = item.extras.map { it.label },
                        quantity = item.quantity,
                        lineTotal = item.itemTotal()
                    )
                }

                val order = Order(
                    orderNumber = orderNumber,
                    dateTime = dateTime,
                    itemsSummary = itemsSummary,
                    totalAmount = totalAmountDollars,
                    lines = lines
                )

                OrderHistoryManager.addOrder(order)

                // ✅ clear cart AFTER saving order
                CartManager.clearCart()

                // ✅ go to thankyou WITH orderNumber
                navController.navigate("thankyou/$orderNumber") {
                    popUpTo("payment") { inclusive = true }
                }
            }

            is PaymentSheetResult.Canceled -> Unit
            is PaymentSheetResult.Failed -> Unit
        }
    }

    fun openPaymentSheet(clientSecret: String) {
        val configuration = PaymentSheet.Configuration(
            merchantDisplayName = "CoffeeApp"
        )
        paymentSheet.presentWithPaymentIntent(clientSecret, configuration)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Payment") },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) { Text("Back") }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
                .padding(bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
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
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(18.dp)
            ) {
                Column(modifier = Modifier.align(Alignment.BottomStart)) {
                    Text(
                        text = "Secure Checkout 🔒",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Pay safely with Stripe test mode",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.92f)
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Order Summary", style = MaterialTheme.typography.titleMedium)
                    SummaryRow("Subtotal", subtotal)
                    SummaryRow("Tax (14%)", tax)
                    Divider()
                    SummaryRow("Total", totalAmountDollars, isTotal = true)

                    Text(
                        text = "Tip: Use Stripe test card 4242 4242 4242 4242",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (!error.isNullOrBlank()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                ) {
                    Text(
                        text = error ?: "",
                        modifier = Modifier.padding(14.dp),
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                enabled = !isLoading,
                onClick = {
                    vm.fetchClientSecret(amountInCents) { clientSecret ->
                        openPaymentSheet(clientSecret)
                    }
                }
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(Modifier.width(10.dp))
                    Text("Preparing...")
                } else {
                    Text("Pay Now")
                }
            }
        }
    }
}

@Composable
private fun SummaryRow(label: String, value: Double, isTotal: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "$${"%.2f".format(value)}",
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium
        )
    }
}