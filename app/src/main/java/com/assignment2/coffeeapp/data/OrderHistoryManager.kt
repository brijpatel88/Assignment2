package com.assignment2.coffeeapp.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

object OrderHistoryManager {

    private val _orders = mutableStateListOf<Order>()
    val orders: List<Order> get() = _orders

    // ✅ lets ThankYouScreen open the right bill
    val lastOrderNumber = mutableStateOf<Int?>(null)

    fun addOrder(order: Order) {
        _orders.add(0, order)
        lastOrderNumber.value = order.orderNumber
    }

    fun getByOrderNumber(orderNumber: Int): Order? {
        return _orders.firstOrNull { it.orderNumber == orderNumber }
    }

    fun clear() {
        _orders.clear()
        lastOrderNumber.value = null
    }
}