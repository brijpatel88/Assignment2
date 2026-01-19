package com.assignment2.coffeeapp.network

import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Retrofit interface for our Payment API.
 * This will call Firebase Cloud Function endpoint.
 */
interface PaymentApi {

    @POST("createPaymentIntent")
    suspend fun createPaymentIntent(
        @Body request: CreatePaymentIntentRequest
    ): CreatePaymentIntentResponse
}