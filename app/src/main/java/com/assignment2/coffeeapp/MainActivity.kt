package com.assignment2.coffeeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.assignment2.coffeeapp.ui.navigation.MainScaffold
import com.assignment2.coffeeapp.ui.theme.CoffeeAppTheme
import com.stripe.android.PaymentConfiguration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Initialize Stripe ONE time for the whole app
        // This reads your pk_test... from BuildConfig (set via local.properties + gradle)
        android.util.Log.d("StripeKey", "PK='${BuildConfig.STRIPE_PUBLISHABLE_KEY}'")
        PaymentConfiguration.init(
            applicationContext,
            BuildConfig.STRIPE_PUBLISHABLE_KEY
        )

        setContent {
            CoffeeAppTheme {
                val navController = rememberNavController()
                MainScaffold(navController)
            }
        }
    }
}