package com.example.androiddevhw_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androiddevhw_2.databinding.ActivityBindingBinding

class BindingActivity : AppCompatActivity() {
    //private  lateinit var binding: ActivityBindingBinding
    private var binding: ActivityBindingBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = ActivityBindingBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.textForBinding
    }
}