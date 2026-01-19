package com.assignment2.coffeeapp.ui.drinks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.assignment2.coffeeapp.data.Drink
import com.assignment2.coffeeapp.data.DrinkDataProvider


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkListScreen(
    navController: NavController,
    category: String
) {
    val drinks = DrinkDataProvider.drinksForCategory(category)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoryTitle(category)) },
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
        ) {

            // Header
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(130.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(categoryGradient(category))
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.align(Alignment.BottomStart)) {
                    Text(
                        text = categoryTitle(category),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Tap a drink to customize size, milk, sugar & extras.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.92f)
                    )
                }
            }

            // Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(drinks) { drink ->
                    DrinkGridCard(
                        drink = drink,
                        category = category,
                        onClick = { navController.navigate("details/${drink.id}") }
                    )
                }
            }
        }
    }
}

@Composable
private fun DrinkGridCard(
    drink: Drink,
    category: String,
    onClick: () -> Unit
) {
    val accent = when (category) {
        "COFFEE" -> Color(0xFF7A4E2D)
        "LATTE" -> Color(0xFFB55A7A)
        "TEA" -> Color(0xFF2E7D5B)
        else -> MaterialTheme.colorScheme.primary
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // ✅ Image area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(accent.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = drink.image),
                    contentDescription = drink.name,
                    modifier = Modifier.size(52.dp)
                )
            }

            // ✅ Name + price only (clean!)
            Column {
                Text(
                    text = drink.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "$${"%.2f".format(drink.price)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = accent
                )
            }
        }
    }
}

private fun categoryTitle(category: String): String {
    return when (category) {
        "COFFEE" -> "Coffee Menu"
        "LATTE" -> "Latte Menu"
        "TEA" -> "Tea Menu"
        else -> "Menu"
    }
}

@Composable
private fun categoryGradient(category: String): Brush {
    return when (category) {
        "COFFEE" -> Brush.linearGradient(listOf(Color(0xFF7A4E2D), Color(0xFFB07D55)))
        "LATTE" -> Brush.linearGradient(listOf(Color(0xFFB55A7A), Color(0xFFE5A4BC)))
        "TEA" -> Brush.linearGradient(listOf(Color(0xFF2E7D5B), Color(0xFF76C7A5)))
        else -> Brush.linearGradient(
            listOf(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.tertiary
            )
        )
    }
}