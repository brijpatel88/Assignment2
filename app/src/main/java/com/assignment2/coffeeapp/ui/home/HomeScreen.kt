package com.assignment2.coffeeapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onCoffeeClick: () -> Unit,
    onLatteClick: () -> Unit,
    onTeaClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {

        //  Colorful gradient HERO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
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
            Column(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Text(
                    text = "CoffeeApp ☕",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Fresh drinks • Fast order • Easy payment",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.92f)
                )
            }
        }

        Spacer(Modifier.height(18.dp))

        Text(
            text = "Browse Categories",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(12.dp))

        // ✅ Modern grid (2 columns)
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ColorCategoryTile(
                    title = "Coffee",
                    subtitle = "Strong & classic",
                    container = Color(0xFF7A4E2D),
                    iconBg = Color(0xFFB07D55),
                    icon = Icons.Filled.Coffee,
                    onClick = onCoffeeClick,
                    modifier = Modifier.weight(1f)
                )
                ColorCategoryTile(
                    title = "Latte",
                    subtitle = "Smooth & milky",
                    container = Color(0xFFB55A7A),
                    iconBg = Color(0xFFE5A4BC),
                    icon = Icons.Filled.LocalCafe,
                    onClick = onLatteClick,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ColorCategoryTile(
                    title = "Tea",
                    subtitle = "Light & relaxing",
                    container = Color(0xFF2E7D5B),
                    iconBg = Color(0xFF76C7A5),
                    icon = Icons.Filled.Spa,
                    onClick = onTeaClick,
                    modifier = Modifier.weight(1f)
                )

                // ✅ “New” tile (optional, looks modern)
                ColorInfoTile(
                    title = "New Items",
                    subtitle = "Coming soon",
                    container = Color(0xFF2B2F3A),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(Modifier.height(14.dp))

        Text(
            text = "Tip: Customize size, milk, sugar & extras on the next screen.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ColorCategoryTile(
    title: String,
    subtitle: String,
    container: Color,
    iconBg: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        color = container,
        shadowElevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = iconBg
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier.padding(12.dp)
                )
            }

            Column {
                Text(title, style = MaterialTheme.typography.titleMedium, color = Color.White)
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }
    }
}

@Composable
private fun ColorInfoTile(
    title: String,
    subtitle: String,
    container: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(120.dp),
        shape = RoundedCornerShape(20.dp),
        color = container,
        shadowElevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White.copy(alpha = 0.16f)
            ) {
                Text(
                    text = "⭐",
                    modifier = Modifier.padding(12.dp),
                    color = Color.White
                )
            }

            Column {
                Text(title, style = MaterialTheme.typography.titleMedium, color = Color.White)
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }
    }
}