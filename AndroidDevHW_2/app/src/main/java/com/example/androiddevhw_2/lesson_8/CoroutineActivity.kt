package com.example.androiddevhw_2.lesson_8

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddevhw_2.databinding.ActivityCoroutineBinding
import com.example.androiddevhw_2.lesson_7.RecyclerAdapter
import com.example.androiddevhw_2.lesson_8.retrofit.CoroutineRetrofitClient
import kotlinx.coroutines.*

class CoroutineActivity : AppCompatActivity() {
    // Job это оболочка для задач которые выполняются на бэкграунде
    private lateinit var job: Job    // Main - главный поток
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

}