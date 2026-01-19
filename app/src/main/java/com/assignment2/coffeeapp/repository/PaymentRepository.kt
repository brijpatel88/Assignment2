package com.assignment2.coffeeapp.repository

import com.assignment2.coffeeapp.network.CreatePaymentIntentRequest
import com.assignment2.coffeeapp.network.RetrofitClient

/**
 * Repository = one place to talk to the API.
 * Keeps ViewModel clean.
 */
class PaymentRepository {

    suspend fun createPaymentIntent(amountInCents: Int): String {
        val response = RetrofitClient.paymentApi.createPaymentIntent(
            CreatePaymentIntentRequest(amount = amountInCents, currency = "cad")
        )
        return response.clientSecret
    }
}