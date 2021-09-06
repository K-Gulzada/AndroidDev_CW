package com.example.androiddevhw_2.lesson_8

import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddevhw_2.databinding.ActivityCoroutineBinding
import com.example.androiddevhw_2.lesson_7.RecyclerAdapter
import com.example.androiddevhw_2.lesson_7.model.User
import com.example.androiddevhw_2.lesson_8.retrofit.CoroutineRetrofitClient
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/*
class CoroutineActivity : AppCompatActivity() {
    // Job это оболочка для задач которые выполняются на бэкграунде
    private lateinit var job: Job    // Main - главный поток - UI Thread
    private val adapter: RecyclerAdapter = RecyclerAdapter()
                                                // Main - главный поток


    private lateinit var binding: ActivityCoroutineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        binding.userRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.userRecyclerView.adapter = adapter
        setContentView(binding.root)

        loadUsers()

    }

    private fun loadUsers() {
        binding.progressBar.visibility = View.VISIBLE
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = CoroutineRetrofitClient.getUsers()
                withContext(Dispatchers.Main) {
                    binding.progressBar.visibility = View.GONE
                    if (response.data.isNullOrEmpty()) {
                        adapter.setData(response.data)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Deferred как promise в javascript-е

}*/

// 06.09.21 coroutine flow

class CoroutineActivity : AppCompatActivity() {
    // Job это оболочка для задач которые выполняются на бэкграунде
    private lateinit var job: Job    // Main - главный поток - UI Thread
    private val adapter: RecyclerAdapter = RecyclerAdapter()
    // Main - главный поток


    private lateinit var binding: ActivityCoroutineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        binding.userRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.userRecyclerView.adapter = adapter
        setContentView(binding.root)

        loadUsers()

    }

    // Flow<Users>

   /* private fun loadUsers() {
        val client = CoroutineRetrofitClient()

        job = CoroutineScope(Dispatchers.Main).launch {
            try {
                client.getUsers()
                    .onStart {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    .catch { exception ->
                        Toast.makeText(
                            this@CoroutineActivity,
                            exception.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .onCompletion { binding.progressBar.visibility = View.GONE }
                    .collect {
                        println("#ACADEMY STEP 1 ${Looper.myLooper() == Looper.getMainLooper()}")
                        adapter.setData(it.data)
                    }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }*/

    // Flow<List<User>> (так лучше не делать)

    private fun loadUsers() {
        val client = CoroutineRetrofitClient()
        val userList:ArrayList<User> = arrayListOf()
        job = CoroutineScope(Dispatchers.Main).launch {
            try {
                client.getUsers()
                    .onStart {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    .catch { exception ->
                        Toast.makeText(
                            this@CoroutineActivity,
                            exception.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .onCompletion { binding.progressBar.visibility = View.GONE }
                    .collect {
                        println("#ACADEMY STEP 1 ${Looper.myLooper() == Looper.getMainLooper()}")
                        userList.addAll(it)
                        adapter.setData(userList)
                    }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}



