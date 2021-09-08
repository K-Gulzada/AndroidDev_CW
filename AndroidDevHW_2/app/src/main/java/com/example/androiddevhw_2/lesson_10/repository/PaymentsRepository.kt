package com.example.androiddevhw_2.lesson_10.repository

interface PaymentsRepository {
    fun loadPayments():List<String>
}