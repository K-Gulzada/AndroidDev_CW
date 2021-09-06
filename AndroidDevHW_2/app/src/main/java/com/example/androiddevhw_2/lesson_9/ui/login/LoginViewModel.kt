package com.example.androiddevhw_2.lesson_9.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevhw_2.lesson_9.LoginRepository
import java.net.PasswordAuthentication

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private val _loginForm = MutableLiveData<LoginFormState>() // источник данных
    val loginFormState: LiveData<LoginFormState> = _loginForm


    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult:LiveData<LoginResult> = _loginResult

    fun login(username:String, password:String){
        val result = loginRepository.login(username, password)
    }
}