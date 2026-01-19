package com.assignment2.coffeeapp.network

/**
 * Request we send to our backend API (Firebase Function).
 * amount is in the SMALLEST currency unit.
 * Example: $12.50 CAD => amount = 1250
 */
data class CreatePaymentIntentRequest(
    val amount: Int,
    val currency: String = "cad"
)

/**
 * Response from our backend API.
 * clientSecret is required by Stripe PaymentSheet.
 */
data class CreatePaymentIntentResponse(
    val clientSecret: String
)