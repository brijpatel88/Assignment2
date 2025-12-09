package com.assignment2.coffeeapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {

    // ---------------------------------------------------------
    // HOME SCREEN – shows 3 drink categories to choose from
    // ---------------------------------------------------------
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Choose Your Drink",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(32.dp))

        // ---------------------------------------------------------
        // Each category is a clickable card
        // ---------------------------------------------------------
        CategoryCard(
            title = "Coffee",
            onClick = { navController.navigate("drinks/COFFEE") }
        )

        CategoryCard(
            title = "Latte",
            onClick = { navController.navigate("drinks/LATTE") }
        )

        CategoryCard(
            title = "Tea",
            onClick = { navController.navigate("drinks/TEA") }
        )
    }
}

@Composable
fun CategoryCard(title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(title, style = MaterialTheme.typography.titleLarge)
        }
    }
}