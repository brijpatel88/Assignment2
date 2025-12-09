package com.assignment2.coffeeapp.ui.drinks

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.assignment2.coffeeapp.R
import com.assignment2.coffeeapp.data.Drink
import com.assignment2.coffeeapp.data.DrinkSize
import com.assignment2.coffeeapp.data.DrinkType

@Composable
fun DrinkListScreen(
    navController: NavController,
    category: String
) {

    // ------------------------------------------------------------
    // 1) Hardcoded drink list for assignment (as required)
    // ------------------------------------------------------------
    val drinks = listOf(
        Drink(
            id = 1,
            name = "Coffee",
            image = R.drawable.coffee,
            shortDesc = "Fresh brewed coffee",
            price = 2.00,
            type = DrinkType.COFFEE,
            sizes = DrinkSize.values().toList()
        ),
        Drink(
            id = 2,
            name = "Latte",
            image = R.drawable.latte,
            shortDesc = "Espresso with steamed milk",
            price = 3.50,
            type = DrinkType.LATTE,
            sizes = DrinkSize.values().toList()
        ),
        Drink(
            id = 3,
            name = "Tea",
            image = R.drawable.tea,
            shortDesc = "Hot steeped tea",
            price = 1.75,
            type = DrinkType.TEA,
            sizes = DrinkSize.values().toList()
        )
    )

    // ------------------------------------------------------------
    // 2) Filter drinks by selected category (Coffee / Latte / Tea)
    // ------------------------------------------------------------
    val filteredDrinks = drinks.filter { it.type.name == category }

    // ------------------------------------------------------------
    // 3) Screen Layout
    // ------------------------------------------------------------
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // Header text
        Text(
            text = "Select Your $category",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        // ------------------------------------------------------------
        // 4) Display each drink using a card item
        // ------------------------------------------------------------
        filteredDrinks.forEach { drink ->
            DrinkListItem(
                drink = drink,
                onClick = {
                    // Navigate to DrinkDetailsScreen
                    navController.navigate("details/${drink.id}")
                }
            )
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
fun DrinkListItem(
    drink: Drink,
    onClick: () -> Unit
) {

    // ------------------------------------------------------------
    // A nice simple card with image, name, price + Order button
    // ------------------------------------------------------------
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {

            // DRINK IMAGE
            Image(
                painter = painterResource(id = drink.image),
                contentDescription = drink.name,
                modifier = Modifier.size(64.dp)
            )

            Spacer(Modifier.width(12.dp))

            // DRINK TEXT INFO
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(drink.name, style = MaterialTheme.typography.titleMedium)
                Text(drink.shortDesc, style = MaterialTheme.typography.bodySmall)
                Text(
                    text = "From $${"%.2f".format(drink.price)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // ORDER BUTTON
            Button(onClick = onClick) {
                Text("Order")
            }
        }
    }
}