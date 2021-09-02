package com.example.androiddevhw_2.lesson_8.retrofit

import com.example.androiddevhw_2.lesson_7.model.Comments
import com.example.androiddevhw_2.lesson_7.model.User
import com.example.androiddevhw_2.lesson_7.model.Users
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface CoroutineApiService {

    @GET("/public/v1/users")
   suspend fun getUsers(): Users

    @GET("/public/v1/comments")
    suspend  fun getComments(): Comments

    @POST("/public/v1/users")
    suspend fun CreateUser(@Body user: User):User
}