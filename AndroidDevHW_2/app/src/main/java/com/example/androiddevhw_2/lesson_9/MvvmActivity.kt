package com.example.androiddevhw_2.lesson_9


import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevhw_2.R
import com.example.androiddevhw_2.databinding.ActivityMvvmBinding
import com.example.androiddevhw_2.lesson_9.state.LoginState
import com.example.androiddevhw_2.lesson_9.ui.login.LoggedInUserView
import com.example.androiddevhw_2.lesson_9.ui.login.LoginFormState
import com.example.androiddevhw_2.lesson_9.ui.login.LoginViewModel
import com.example.androiddevhw_2.lesson_9.ui.login.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_mvvm.*


/*import android.app.Activity
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevhw_2.BindingActivity
import com.example.androiddevhw_2.lesson_9.ui.login.LoginViewModelFactory*/

class MvvmActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityMvvmBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMvvmBinding.inflate(layoutInflater)
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

        /* loginViewModel.loginFormState.observe(this@MVVMActivity, Observer {
             val loginState = it ?: return@Observer

             login.isEnabled = loginState.isDataValid

             if (loginState.usernameError != null) {
                 username.error = getString(loginState.usernameError)
             }

             if (loginState.passwordError != null) {
                 password.error = getString(loginState.passwordError)
             }

         })*/

        /* loginViewModel.loginResult.observe(this@MVVMActivity, Observer {
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
         })*/

        /* username.setOnFocusChangeListener { _, hasFocus ->
             if (!hasFocus) {
                 username.afterTextChanged {
                     val flag = loginViewModel.emailCheck(
                         username.text.toString()
                     )
                     if (flag) {
                         LoginFormState(usernameError = R.string.invalid_username)
                         username.setTextColor(Color.RED);
                     }
                     else{
                         username.setTextColor(Color.GREEN);
                     }
                 }
             }
         }*/
        var flag1 = false
        var flag2 = false

        username.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {

                flag1 = loginViewModel.emailCheck(
                    username.text.toString()
                )
                if (!flag1) {
                    LoginFormState(usernameError = R.string.invalid_username)
                    username.setTextColor(Color.RED);
                } else {
                    username.setTextColor(Color.GREEN);
                }

            }
        }


        password.apply {
            afterTextChanged {
                flag2 = loginViewModel.passwordCheck(
                    password.text.toString()
                )
                if (!flag2) {
                    password.setTextColor(Color.RED);
                    LoginFormState(passwordError = R.string.invalid_password)
                } else {
                    password.setTextColor(Color.GREEN);
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