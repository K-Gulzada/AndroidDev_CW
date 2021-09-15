package com.example.androiddevhw_2.lesson_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.androiddevhw_2.R
import kotlinx.android.synthetic.main.activity_pref.*

class PrefActivity : AppCompatActivity() {
    companion object{
        const val PREFERENCES = "PrefActivityPreferences"
        const val COUNTER = "counter"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pref)

        setView()
    }

    private fun setView(){
        val counter = findViewById<TextView>(R.id.counter)
        val increment = findViewById<TextView>(R.id.counter_btn)

        val preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE)
        val preferencesEditor = preferences.edit()
        val count = preferences.getInt(COUNTER, 0)

        counter.text = count.toString()

        increment.setOnClickListener{
            preferencesEditor.putInt(COUNTER, count + 1)
            preferencesEditor.apply()
        }


    }
}