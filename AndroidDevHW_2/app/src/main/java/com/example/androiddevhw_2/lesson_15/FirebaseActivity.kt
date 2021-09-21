package com.example.androiddevhw_2.lesson_15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.androiddevhw_2.R
import java.lang.RuntimeException

class FirebaseActivity : AppCompatActivity() {

  /*  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_firebase)

        findViewById<Button>(R.id.btn).setOnClickListener {
            Analytics.logEvent(Analytics.Event("LOGIN", listOf(Pair("bnt_click", "login"))))
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val remoteConfig = RemoteConfig()
        remoteConfig.initialize(this)
        setContentView(R.layout.activity_firebase)
        if (remoteConfig.getBoolean("login_btn_enabled")) {
            findViewById<Button>(R.id.btn).visibility = View.VISIBLE
        }
    }
}