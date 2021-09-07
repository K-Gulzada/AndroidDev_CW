package com.example.androiddevhw_2.lesson_9.state

import com.example.androiddevhw_2.lesson_9.ui.login.LoggedInUserView

sealed class LoginState {
    object Loading : LoginState()
    class LoginError(val errorInfo: Int) : LoginState()
    class PasswordError(val errorInfo: Int) : LoginState()
    class AuthenticationError(val errorInfo: Int) : LoginState()
    class Success(val success: LoggedInUserView) : LoginState()
    object DataIsValid : LoginState()

}