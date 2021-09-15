package com.example.androiddevhw_2.lesson_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddevhw_2.R
import com.example.androiddevhw_2.databinding.ActivityRoomBinding
import com.example.androiddevhw_2.lesson_12.room.Repository
import com.example.androiddevhw_2.lesson_12.room.User
import com.example.androiddevhw_2.lesson_12.room.UserDatabase
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    private val adapter = UserRecyclerAdapter()
    private val userDatabase: UserDatabase by lazy { Repository.database }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViews()
    }

    private fun setViews() {
        binding.apply {
            recycler.layoutManager = LinearLayoutManager(this@RoomActivity)
            recycler.adapter = adapter
            CoroutineScope(Dispatchers.IO).launch {
                userDatabase.userDao().getAllUsers().collect {
                    withContext(Dispatchers.Main) {
                        adapter.setData(it)
                    }
                }
            }

            registerBtn.setOnClickListener {
                val user = User(
                    firstName = first_name.text.toString(),
                    secondName = last_name.text.toString()
                )

                CoroutineScope(Dispatchers.IO).launch {
                    userDatabase.userDao().addUser(user)
                }


            }
            deleteBtn.setOnClickListener {
                val firstname = firstName.text.toString()
                val secondname = lastName.text.toString()

                CoroutineScope(Dispatchers.IO).launch {
                    userDatabase.userDao().deleteByNameAndLastName(firstname, secondname)
                }

            }

        }
    }
}