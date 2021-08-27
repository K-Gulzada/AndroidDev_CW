package com.example.androiddevhw_2.lesson_7.model

import java.util.*

data class Users(val data: ArrayList<User>)

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val gender: Gender,
    val status: Status,
    val field:String?,
    val message:String?
    )

enum class Gender {
    male,
    female;
}

enum class Status {
    active,
    inactive;
}