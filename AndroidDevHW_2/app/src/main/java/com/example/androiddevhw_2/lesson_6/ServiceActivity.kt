package com.example.androiddevhw_2.lesson_6

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.androiddevhw_2.R


/*class ServiceActivity : AppCompatActivity() {
    private val receiver = TestBroadcastReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}*/

//сами являемся источником
/*
class ServiceActivity : AppCompatActivity() {
    private val receiver = TestBroadcastReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        findViewById<Button>(R.id.intent_btn).setOnClickListener{
            val intent = Intent()
            intent.setAction("com.example.androiddevhw_2.lesson_6.NOTIFICATION")
            intent.putExtra("data", "TestData")
            sendBroadcast(intent)
        }

        val filter = IntentFilter("com.example.androiddevhw_2.lesson_6.NOTIFICATION")
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}*/

// тема - Service

class ServiceActivity : AppCompatActivity() {
    private val receiver = TestBroadcastReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        findViewById<Button>(R.id.intent_btn).setOnClickListener{
            val intent = Intent()
            intent.setAction("com.example.androiddevhw_2.lesson_6.NOTIFICATION")
            intent.putExtra("data", "TestData")
            sendBroadcast(intent)
        }

        val filter = IntentFilter("com.example.androiddevhw_2.lesson_6.NOTIFICATION")
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}