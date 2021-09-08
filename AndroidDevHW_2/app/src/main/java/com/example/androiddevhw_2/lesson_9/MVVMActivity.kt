package com.example.androiddevhw_2.lesson_9

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevhw_2.BindingActivity
import com.example.androiddevhw_2.R
import com.example.androiddevhw_2.databinding.ActivityMvvmactivityBinding
import com.example.androiddevhw_2.lesson_9.state.LoginState
import com.example.androiddevhw_2.lesson_9.ui.login.LoggedInUserView
import com.example.androiddevhw_2.lesson_9.ui.login.LoginFormState
import com.example.androiddevhw_2.lesson_9.ui.login.LoginViewModel
import com.example.androiddevhw_2.lesson_9.ui.login.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_mvvmactivity.*

class MVVMActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityMvvmactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMvvmactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.state.observe(this, Observer {

            when (it) {
                LoginState.Loading -> {
                    loading.visibility = View.VISIBLE
                }
                is LoginState.Success -> {
                }
                is LoginState.LoginError -> {
                    toggleLoginBtn(false)
                }
                is LoginState.PasswordError -> {
                    toggleLoginBtn(false)
                }
                is LoginState.AuthenticationError -> {

                }
                is LoginState.DataIsValid -> {
                    toggleLoginBtn(true)
                }
            }
            return@Observer
        })

        var flag1 = false
        var flag2 = false

        username.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {

                flag1 = loginViewModel.emailCheck(
                    username.text.toString()
                )
                if (!flag1) {
                    LoginFormState(usernameError = R.string.invalid_username)

                    username.error = getString(R.string.invalid_username)
                }

                loginViewModel.checkData(flag1, flag2)
            }
        }


        password.apply {
            afterTextChanged {
                flag2 = loginViewModel.passwordCheck(
                    password.text.toString()
                )
                if (!flag2) {
                    password.error = getString(R.string.invalid_password)
                }else{
                    password.error = null
                }

                loginViewModel.checkData(flag1, flag2)
            }

        }

        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            loginViewModel.login(username.text.toString(), password.text.toString())
        }
    }

    private fun toggleLoginBtn(isEnabled: Boolean) {
        login.isEnabled = isEnabled
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName

        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

    })
}

// Unit как void ничего не возвращает (на 122 строке мы принимаем String и ничего не возвращаем)
