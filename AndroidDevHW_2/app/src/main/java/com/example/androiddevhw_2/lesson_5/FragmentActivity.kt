package com.example.androiddevhw_2.lesson_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androiddevhw_2.R

/*
class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout, StartFragment.newInstance("text1","text2"))
            .commit()
    }

    fun onNext(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout, FinishFragment.newInstance("Finish Fragment Text 1","Just text"))
            .commit()
    }
}*/

// после того как добавили navigation

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

    }


}