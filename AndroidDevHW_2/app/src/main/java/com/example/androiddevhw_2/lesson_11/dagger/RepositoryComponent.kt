package com.example.androiddevhw_2.lesson_11.dagger

import com.example.androiddevhw_2.lesson_11.DaggerActivity
import com.example.androiddevhw_2.lesson_11.dagger.RepositoryModule
import dagger.Component


@Component(modules = [RepositoryModule::class])
    interface RepositoryComponent {

        fun injectIntoActivity(activity: DaggerActivity)
    }