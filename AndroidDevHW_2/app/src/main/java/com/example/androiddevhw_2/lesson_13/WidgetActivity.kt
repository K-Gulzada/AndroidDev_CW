package com.example.androiddevhw_2.lesson_13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androiddevhw_2.R
import com.example.androiddevhw_2.lesson_13.widgets.FeedbackWidget

class WidgetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)

        findViewById<FeedbackWidget>(R.id.feedback_widget).onEndIconClick {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}