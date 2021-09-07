package com.example.androiddevhw_2.lesson_9.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevhw_2.lesson_9.LoginDataSource
import com.example.androiddevhw_2.lesson_9.LoginRepository
import java.lang.IllegalArgumentException

class LoginViewModelFactory:ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            )as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}