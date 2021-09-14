package com.example.domain.datasource.impl

import com.example.data.User
import com.example.domain.datasource.DataSource
import com.example.domain.datasource.remote.Api

class RemoteDataSourceImpl(private val api: Api) : DataSource {
    override fun getUser(): User {
        return api.getUser()
    }
}