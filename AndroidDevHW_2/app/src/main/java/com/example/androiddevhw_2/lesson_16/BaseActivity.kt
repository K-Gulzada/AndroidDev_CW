package com.example.androiddevhw_2.lesson_16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.androiddevhw_2.R

class BaseActivity : AppCompatActivity() {

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        editText = findViewById(R.id.inputField)
    }

    fun onClick(view:View){
        when(view.id){
            R.id.changeText->editText.setText("Lalala")
            R.id.switchActivity->{
                val intent=Intent(this, SecondActivity::class.java)
                intent.putExtra("input", editText.text.toString())
            }
        }
    }
}