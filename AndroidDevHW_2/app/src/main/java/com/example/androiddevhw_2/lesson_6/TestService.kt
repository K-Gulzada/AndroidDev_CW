package com.example.androiddevhw_2.lesson_6

import android.app.Service
import android.content.Intent

import android.os.*

import java.lang.Exception
import java.lang.Process

class TestService : Service() {
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHelper? = null

    override fun onCreate() {
        super.onCreate()
        HandlerThread("Arguments").apply {
            start()
            serviceLooper = looper
            serviceHandler = ServiceHelper(looper)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("Academy Step service start")

        serviceHandler?.obtainMessage()?.also { msg->
            msg.arg1 = startId
            serviceHandler?.sendMessage(msg)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        println("Academy Step service destroyed")
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private inner class ServiceHelper(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            try {
                Thread.sleep(5000)
            } catch (e: Exception) {
                Thread.currentThread().interrupt()
            }

            stopSelf(msg.arg1)
        }

    }


}