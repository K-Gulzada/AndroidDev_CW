package com.example.androiddevhw_2.lesson_10.model

import com.example.androiddevhw_2.lesson_10.repository.PaymentsRepository

class PaymentsModel(private val repository: PaymentsRepository) {

    val payments: List<String>
        get() = repository.loadPayments()
}