package com.example.androiddevhw_2.lesson_9.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevhw_2.R
import com.example.androiddevhw_2.lesson_9.LoginRepository
import com.example.androiddevhw_2.lesson_9.Result
import com.example.androiddevhw_2.lesson_9.state.LoginState

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private val _state = MutableLiveData<LoginState>() // источник данных
    val state: LiveData<LoginState> = _state

    fun login(username: String, password: String) {
        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            _state.value =
                LoginState.Success(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _state.value = LoginState.AuthenticationError(R.string.login_failed)
        }
    }

    fun checkData(){

    }

    fun emailCheck(email: String) {
        if (!isUserNameValid(email)) {
            _state.value = LoginState.LoginError(R.string.invalid_username)
        } else {
            // We will make a really good piece of code here
            // trust me
        }
    }

    fun passwordCheck(password: String) {
        if (!isPasswordValid(password)) {
            _state.value = LoginState.PasswordError(R.string.invalid_password)
        } else {
            // We will make a really good piece of code here
            // trust me
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}