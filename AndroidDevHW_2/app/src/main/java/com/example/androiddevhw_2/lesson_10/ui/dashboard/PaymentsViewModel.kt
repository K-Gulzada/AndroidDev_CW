package com.example.androiddevhw_2.lesson_10.ui.dashboard

import androidx.lifecycle.ViewModel
import com.example.androiddevhw_2.lesson_10.repository.PaymentsRepository

class PaymentsViewModel(private val paymentsRepository: PaymentsRepository ) : ViewModel() {
    val payments: List<String>
        get() = paymentsRepository.loadPayments()
}