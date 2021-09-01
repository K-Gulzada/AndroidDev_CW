package com.example.androiddevhw_2.lesson_7.retrofit


import com.example.androiddevhw_2.lesson_7.model.User
import com.example.androiddevhw_2.lesson_7.model.Users
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("/public/v1/users")
    fun getUsers(): Observable<Users>

    @POST("/public/v1/users")
    fun CreateUser(@Body user: User):Observable<User>
}