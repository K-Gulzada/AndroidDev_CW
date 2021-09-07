package com.example.androiddevhw_2.lesson_9

import android.app.Activity
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
import com.example.androiddevhw_2.lesson_9.ui.login.LoginViewModel
import com.example.androiddevhw_2.lesson_9.ui.login.LoginViewModelFactory

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
        })

        loginViewModel.loginFormState.observe(this@MVVMActivity, Observer {
            val loginState = it ?: return@Observer

            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }

            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }

        })

        loginViewModel.loginResult.observe(this@MVVMActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }

            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }

            setResult(Activity.RESULT_OK)

            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
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
