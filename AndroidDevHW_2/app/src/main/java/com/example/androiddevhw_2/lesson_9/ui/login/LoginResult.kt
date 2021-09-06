package com.example.androiddevhw_2.lesson_9.ui.login

data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)