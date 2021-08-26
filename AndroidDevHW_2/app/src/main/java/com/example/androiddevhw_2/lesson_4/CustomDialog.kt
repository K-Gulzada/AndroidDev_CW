package com.example.androiddevhw_2.lesson_4

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import com.example.androiddevhw_2.R

class CustomDialog(context: Context) : Dialog(context), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog)
        val yesBtn = findViewById<Button>(R.id.btn_yes)
        val noBtn = findViewById<Button>(R.id.btn_no)
        yesBtn.setOnClickListener(this)
        noBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
       when(v.id){
           R.id.btn_yes->dismiss()
           R.id.btn_no->dismiss()
           else->{

           }
       }
        dismiss()
    }
}