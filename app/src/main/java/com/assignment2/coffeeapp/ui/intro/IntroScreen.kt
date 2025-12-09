package com.assignment2.coffeeapp.ui.intro

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun IntroScreen(navController: NavController) {

    // ----------------------------------------------
    // 👋 INTRO / WELCOME SCREEN
    // First screen the user sees
    // ----------------------------------------------
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ----------------------------------------------
        // APP TITLE
        // ----------------------------------------------
        Text(
            text = "Tim Hortons Coffee App",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(12.dp))

        // Subtitle / Tagline
        Text(
            text = "Order your Coffee, Latte, or Tea.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(40.dp))

        // ----------------------------------------------
        // START ORDER BUTTON
        // Navigates to HomeScreen
        // ----------------------------------------------
        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text("Start Order")
        }
    }
}