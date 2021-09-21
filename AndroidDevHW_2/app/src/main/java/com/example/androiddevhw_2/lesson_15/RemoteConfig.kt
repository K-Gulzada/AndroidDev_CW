package com.example.androiddevhw_2.lesson_15

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import com.example.androiddevhw_2.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class RemoteConfig {
    private lateinit var remoteConfig: FirebaseRemoteConfig
    private val configs = FirebaseRemoteConfigSettings.Builder()
        .setMinimumFetchIntervalInSeconds(100)
        .build()

    fun initialize(context: Context) {
        remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.setConfigSettingsAsync(configs)
        remoteConfig.setDefaultsAsync(R.xml.config_defaults)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(
                context as Activity,
                object : OnCompleteListener<Boolean> {
                    override fun onComplete(task: Task<Boolean>) {
                        if(task.isSuccessful){
                            println("Success")
                        }else{
                            Log.e("Error", "Error")
                        }
                    }
                })
    }

    fun getBoolean(key:String):Boolean{
        return remoteConfig.getBoolean(key)
    }
}