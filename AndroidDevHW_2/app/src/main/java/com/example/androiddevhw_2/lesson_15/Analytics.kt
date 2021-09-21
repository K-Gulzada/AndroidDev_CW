package com.example.androiddevhw_2.lesson_15

import android.os.Bundle
import com.example.androiddevhw_2.lesson_7.model.Gender
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

object Analytics {
    private const val EVENT_MESSAGE = "eventMessage"
    private const val GENDER = "gender"
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    fun init() {
        firebaseAnalytics = Firebase.analytics
    }

    fun setGender(gender: Gender){
        firebaseAnalytics.setUserProperty(GENDER, gender.name)
    }

    fun logEvent(event: Event) {
        val bundle = Bundle()
        event.list.forEach{
            bundle.putString(it.first, it.second)
        }

        firebaseAnalytics.logEvent(event.eventName, bundle)
    }

    class Event(
        val eventName: String,
        val list:List<Pair<String, String>>
    )
}