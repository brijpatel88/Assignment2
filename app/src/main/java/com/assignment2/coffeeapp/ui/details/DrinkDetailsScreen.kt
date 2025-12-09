package com.assignment2.coffeeapp.ui.details

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.assignment2.coffeeapp.R
import com.assignment2.coffeeapp.data.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkDetailsScreen(
    navController: NavController,
    drinkId: Int
) {
    Log.d("DETAILS", "DrinkDetailsScreen opened with drinkId = $drinkId")

    // -------------------------------------------------------------------
    // 1) Static drink list (required by assignment)
    // -------------------------------------------------------------------
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

    // Find drink by ID
    val drink = drinks.firstOrNull { it.id == drinkId }
        ?: drinks.first().also {
            Log.w("DETAILS", "Invalid drinkId=$drinkId. Defaulting to id=${it.id}")
        }

    // -------------------------------------------------------------------
    // 2) UI State
    // -------------------------------------------------------------------
    var selectedSize by remember { mutableStateOf(DrinkSize.SMALL) }
    var expanded by remember { mutableStateOf(false) }

    // -------------------------------------------------------------------
    // 3) Screen Layout
    // -------------------------------------------------------------------
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // DRINK IMAGE
        Image(
            painter = painterResource(drink.image),
            contentDescription = drink.name,
            modifier = Modifier.size(200.dp)
        )

        Spacer(Modifier.height(20.dp))

        // DRINK DETAILS
        Text(drink.name, style = MaterialTheme.typography.headlineSmall)
        Text(drink.shortDesc, style = MaterialTheme.typography.bodyMedium)
        Text("Base Price: $${drink.price}", style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(20.dp))


        // -------------------------------------------------------------------
        // ⭐ FIXED + PROPER MATERIAL3 DROPDOWN (Anchored Correctly)
        // -------------------------------------------------------------------
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

            // Anchor uses an OutlinedTextField (recommended for M3 menus)
            OutlinedTextField(
                value = selectedSize.label,
                onValueChange = {},
                readOnly = true,
                label = { Text("Choose Size") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),

                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                },

                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )

            // Dropdown Menu Items
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DrinkSize.values().forEach { size ->
                    DropdownMenuItem(
                        text = { Text(size.label) },
                        onClick = {
                            selectedSize = size
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(40.dp))


        // -------------------------------------------------------------------
        // ADD TO CART BUTTON
        // -------------------------------------------------------------------
        Button(
            onClick = {
                CartManager.addToCart(CartItem(drink, selectedSize))
                navController.navigate("cart")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add to Cart")
        }
    }
}