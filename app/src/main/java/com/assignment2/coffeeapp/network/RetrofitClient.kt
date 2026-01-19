package com.assignment2.coffeeapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Simple Retrofit builder.
 * Replace BASE_URL with your Firebase Function URL later.
 */
object RetrofitClient {

    //  You will paste your real Firebase Functions base URL here later
    // Example:
    // https://us-central1-YOUR_PROJECT_ID.cloudfunctions.net/
    const val BASE_URL = "http://10.0.2.2:4242/"

    private val okHttp = OkHttpClient.Builder()
        .build()

    val paymentApi: PaymentApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PaymentApi::class.java)
    }
}