package com.assignment2.coffeeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment2.coffeeapp.repository.PaymentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Holds the UI state for payments.
 * - Requests clientSecret from backend
 * - Exposes loading/error states
 */
class PaymentViewModel(
    private val repo: PaymentRepository = PaymentRepository()
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun clearError() {
        _error.value = null
    }

    /**
     * Call backend to create a PaymentIntent and return the clientSecret via callback.
     */
    fun fetchClientSecret(
        amountInCents: Int,
        onSuccess: (clientSecret: String) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val secret = repo.createPaymentIntent(amountInCents)
                onSuccess(secret)
            } catch (e: Exception) {
                _error.value = e.message ?: "Payment API error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}