package com.example.domain.repository.impl

import com.example.data.User
import com.example.domain.datasource.DataSource

import com.example.domain.repository.Repository

class RepositoryImpl(private val remoteDataSource: DataSource): Repository {
    override fun getUser(): User {
        return remoteDataSource.getUser()
    }

}
