package com.example.androiddevhw_2

import android.app.Application
import com.example.androiddevhw_2.lesson_10.module.paymentsModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // 09.09.21 koin
       /* startKoin{
            androidLogger()
            androidContext(this@App)
            modules(paymentsModule)
        }*/

        // 13.09.21 dagger
    }
}