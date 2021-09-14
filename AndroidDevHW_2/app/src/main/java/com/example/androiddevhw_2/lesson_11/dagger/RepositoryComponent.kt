package com.example.androiddevhw_2.lesson_11.dagger

import com.example.androiddevhw_2.lesson_11.DaggerActivity
import dagger.Component

@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {

    fun injectIntoActivity(acitivity: DaggerActivity)
}