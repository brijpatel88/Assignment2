package com.assignment2.coffeeapp.ui.details

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.assignment2.coffeeapp.data.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkDetailsScreen(
    navController: NavController,
    drinkId: Int
) {
    Log.d("DETAILS", "DrinkDetailsScreen opened with drinkId = $drinkId")

    // ✅ Pull drink from the single source of truth
    val drink = DrinkDataProvider.getById(drinkId)

    // ✅ If not found, show a friendly screen
    if (drink == null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Drink Details") },
                    navigationIcon = {
                        TextButton(onClick = { navController.popBackStack() }) { Text("Back") }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Sorry, this drink was not found.", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(12.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Go Back")
                }
            }
        }
        return
    }

    // ✅ Favorite state (reactive)
    val isFavorite = remember(drink.id) { mutableStateOf(FavoritesManager.isFavorite(drink.id)) }
    LaunchedEffect(drink.id) {
        isFavorite.value = FavoritesManager.isFavorite(drink.id)
    }

    // -------------------------------------------------------------------
    // UI STATE
    // -------------------------------------------------------------------
    var selectedSize by remember { mutableStateOf(DrinkSize.SMALL) }
    var selectedMilk by remember { mutableStateOf(MilkType.REGULAR) }
    var selectedSugar by remember { mutableStateOf(SugarLevel.ONE) }
    var selectedExtras by remember { mutableStateOf(setOf<Extra>()) }

    var sizeExpanded by remember { mutableStateOf(false) }
    var milkExpanded by remember { mutableStateOf(false) }
    var sugarExpanded by remember { mutableStateOf(false) }

    // -------------------------------------------------------------------
    // UI
    // -------------------------------------------------------------------
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(drink.name) },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) { Text("Back") }
                },
                actions = {
                    // ✅ Favorite toggle in the top bar
                    IconButton(
                        onClick = {
                            FavoritesManager.toggle(drink.id)
                            isFavorite.value = FavoritesManager.isFavorite(drink.id)
                        }
                    ) {
                        Icon(
                            imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(drink.image),
                contentDescription = drink.name,
                modifier = Modifier.size(200.dp)
            )

            Spacer(Modifier.height(18.dp))

            Text(drink.name, style = MaterialTheme.typography.headlineSmall)
            Text(drink.shortDesc, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(6.dp))
            Text("Base Price: $${"%.2f".format(drink.price)}")

            Spacer(Modifier.height(20.dp))

            // ---------------- SIZE ----------------
            ExposedDropdownMenuBox(
                expanded = sizeExpanded,
                onExpandedChange = { sizeExpanded = !sizeExpanded }
            ) {
                OutlinedTextField(
                    value = selectedSize.label,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Size") },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(sizeExpanded) }
                )
                ExposedDropdownMenu(
                    expanded = sizeExpanded,
                    onDismissRequest = { sizeExpanded = false }
                ) {
                    DrinkSize.values().forEach {
                        DropdownMenuItem(
                            text = { Text(it.label) },
                            onClick = {
                                selectedSize = it
                                sizeExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // ---------------- MILK ----------------
            ExposedDropdownMenuBox(
                expanded = milkExpanded,
                onExpandedChange = { milkExpanded = !milkExpanded }
            ) {
                OutlinedTextField(
                    value = selectedMilk.label,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Milk") },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(milkExpanded) }
                )
                ExposedDropdownMenu(
                    expanded = milkExpanded,
                    onDismissRequest = { milkExpanded = false }
                ) {
                    MilkType.values().forEach {
                        DropdownMenuItem(
                            text = { Text(it.label) },
                            onClick = {
                                selectedMilk = it
                                milkExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // ---------------- SUGAR ----------------
            ExposedDropdownMenuBox(
                expanded = sugarExpanded,
                onExpandedChange = { sugarExpanded = !sugarExpanded }
            ) {
                OutlinedTextField(
                    value = selectedSugar.label,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Sugar") },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(sugarExpanded) }
                )
                ExposedDropdownMenu(
                    expanded = sugarExpanded,
                    onDismissRequest = { sugarExpanded = false }
                ) {
                    SugarLevel.values().forEach {
                        DropdownMenuItem(
                            text = { Text(it.label) },
                            onClick = {
                                selectedSugar = it
                                sugarExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(18.dp))

            // ---------------- EXTRAS ----------------
            Text("Extras", style = MaterialTheme.typography.titleMedium)

            Extra.values().forEach { extra ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = selectedExtras.contains(extra),
                        onCheckedChange = {
                            selectedExtras =
                                if (selectedExtras.contains(extra)) selectedExtras - extra
                                else selectedExtras + extra
                        }
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("${extra.label} (+$${"%.2f".format(extra.price)})")
                }
            }

            Spacer(Modifier.height(24.dp))

            // ---------------- ADD TO CART ----------------
            Button(
                onClick = {
                    CartManager.addToCart(
                        CartItem(
                            drink = drink,
                            size = selectedSize,
                            milk = selectedMilk,
                            sugar = selectedSugar,
                            extras = selectedExtras.toList()
                        )
                    )
                    navController.navigate("cart")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add to Cart")
            }
        }
    }
}