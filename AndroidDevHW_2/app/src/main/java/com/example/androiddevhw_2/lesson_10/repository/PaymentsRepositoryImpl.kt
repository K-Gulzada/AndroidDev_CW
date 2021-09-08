package com.example.androiddevhw_2.lesson_10.repository

class PaymentsRepositoryImpl:PaymentsRepository {
    override fun loadPayments(): List<String> {
        return listOf("Mobile", "KSK")
    }
}