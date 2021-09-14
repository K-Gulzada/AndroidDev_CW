package com.example.domain.repository

import com.example.data.User

interface Repository {
    fun getUser(): User
}