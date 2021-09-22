package com.example.androiddevhw_2.lesson_16

interface DataSource {

    fun postRequest(input: String): Int
    fun getRequest(params: Array<String>): List<Int>
}