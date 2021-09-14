package com.example.androiddevhw_2.lesson_11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.androiddevhw_2.R
//import com.example.androiddevhw_2.lesson_11.dagger.DaggerRepositoryComponent

import com.example.domain.repository.Repository
import dagger.hilt.android.AndroidEntryPoint
//import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DaggerActivity : AppCompatActivity() {
    @Inject
    lateinit var repository: Repository

    // @Inject
    // lateinit var repositoryComponent: RepositoryComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)

        // ак как hilt добавили вручную инжектить не нужно, закоментим.
        // Hilt как мост между андроидом и dagger упрощает работу с dagger
        // DaggerRepositoryComponent.builder().build().injectIntoActivity(this)

        // repositoryComponent.injectIntoActivity(this)
        //injectIntoActivity(this)

       // findViewById<TextView>(R.id.userText).text = repository.getUser().toString()


    }
}