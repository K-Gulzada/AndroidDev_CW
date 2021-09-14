package com.example.domain.datasource

import com.example.data.User

interface DataSource {
    fun getUser(): User
}