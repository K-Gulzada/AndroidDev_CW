package com.example.androiddevhw_2.lesson_7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.androiddevhw_2.R
import com.example.androiddevhw_2.databinding.ActivityRxRegisterBinding
import com.example.androiddevhw_2.lesson_7.model.Gender
import com.example.androiddevhw_2.lesson_7.model.Status
import com.example.androiddevhw_2.lesson_7.model.User
import com.example.androiddevhw_2.lesson_7.model.Users
import com.example.androiddevhw_2.lesson_7.retrofit.RetrofitClient
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RxRegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRxRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRxRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.apply {
            userGender.adapter = ArrayAdapter
                                .createFromResource(this@RxRegisterActivity, R.array.spinner,
                                                    android.R.layout.simple_spinner_item)

            registerBtn.setOnClickListener{
                val email = email.text.toString()
                val name=userName.text.toString()
                val id = userId.text.toString()
                val gender = Gender.valueOf(userGender.selectedItem.toString())

                if(email.isNullOrEmpty() || name.isNullOrEmpty()||id.isNullOrEmpty()){
                    return@setOnClickListener
                }
                val user = User(id.toInt(), name, email, gender, Status.active)
                //registerUser(user)
                val intent = Intent(this@RxRegisterActivity, RxAcitivity::class.java)
                intent.putExtra(RxAcitivity.USER, Json.encodeToString(user))
                startActivity(intent)

            }
        }
    }

    private fun registerUser(user:User){
        binding.progressBar.visibility = View.VISIBLE

        RetrofitClient.createUsers(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<User> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: User) {
                    hideProgress()
                    val intent = Intent(this@RxRegisterActivity, RxAcitivity::class.java)
                    startActivity(intent)
                }

                override fun onError(e: Throwable) {
                    hideProgress()
                    Toast.makeText(this@RxRegisterActivity, "Error: $e", Toast.LENGTH_SHORT).show()

                }

                override fun onComplete() {
                    hideProgress()
                }

            })
    }

    private fun hideProgress(){
        binding.progressBar.visibility = View.GONE
    }
}