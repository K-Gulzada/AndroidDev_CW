package com.example.androiddevhw_2.lesson_6

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import com.example.androiddevhw_2.R
import java.lang.Exception


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
/*

class ServiceActivity : AppCompatActivity() {
    private val receiver = TestBroadcastReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        findViewById<Button>(R.id.intent_btn).setOnClickListener{
            val intent = Intent(this, TestService::class.java)
            startService(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}*/

// после создания testForegroundService


class ServiceActivity : AppCompatActivity() {
    private val receiver = TestBroadcastReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        findViewById<Button>(R.id.intent_btn).setOnClickListener {
            val intent = Intent(this, TestForegroundService::class.java)
            startService(intent)
            updateStatus()
        }

        findViewById<Button>(R.id.stop_service).setOnClickListener {
            val intent = Intent(this, TestForegroundService::class.java)
            intent.action = TestForegroundService.ACTION_STOP
            startService(intent)

            Handler().postDelayed({updateStatus()}, 100)
        }
    }

    private fun updateStatus() {
        if (isServiceRunning(TestForegroundService::class.java)) {
            findViewById<TextView>(R.id.service_status).setText("Service is Running")

        }else{
            findViewById<TextView>(R.id.service_status).setText("Service is closed")
        }
    }

    @SuppressWarnings("deprecation")
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        try {
            val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            for (service in manager.getRunningServices(Int.MAX_VALUE)) {
                if (service.service.className == serviceClass.name) {
                    return true
                }
            }
        } catch (e: Exception) {
            return false
        }
        return false

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}