package com.assignment2.coffeeapp.data

data class Order(
    val orderNumber: Int,
    val dateTime: String,
    val itemsSummary: String,
    val totalAmount: Double,
    val lines: List<OrderLine> // ✅ NEW: for bill + reorder
)