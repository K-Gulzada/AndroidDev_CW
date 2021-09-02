package com.example.androiddevhw_2.lesson_7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevhw_2.R
import com.example.androiddevhw_2.lesson_7.model.Comment
import com.example.androiddevhw_2.lesson_7.model.Comments
import com.example.androiddevhw_2.lesson_7.model.User
import com.example.androiddevhw_2.lesson_7.model.Users
import com.example.androiddevhw_2.lesson_7.retrofit.RetrofitClient
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import java.sql.Wrapper


import kotlin.collections.ArrayList

// RXJava/RXAndroid библиотека (для работы с потоками, паттерн обзёрабл и т.д.)


class RxAcitivity : AppCompatActivity() {
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var commentRecyclerView: RecyclerView

    private lateinit var progressBar: ProgressBar

    private val userAdapter: RecyclerAdapter = RecyclerAdapter()
    private val commentAdapter: CommentsAdapter = CommentsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_acitivity)

        userRecyclerView = findViewById(R.id.user_recycler_view)
        commentRecyclerView = findViewById(R.id.comments_recycler_view)
        progressBar = findViewById(R.id.progress_bar)

        /*  findViewById<Button>(R.id.create_user_btn).setOnClickListener {

          }*/
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        commentRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = userAdapter
        commentRecyclerView.adapter = commentAdapter

        fetchData()

        // 01.09.21

        //fetchUsers наверху закоментим
        /*   val userString = intent.getStringExtra(USER)
    val user = Json.decodeFromString<User>(
        userString ?: throw Exception("User not Found")
    )
    println("ACADEMY $userString")
    adapter.setData(arrayListOf(user))*/

        // 02.09.21
        // обратно раскоментим fetchUsers(), а 01,09,21 закоментим
        // Добавили commentRecyclerView, commentAdapter,  commentRecyclerView.layoutManager,
        // commentRecyclerView.adapter


    }

    //01.09.21================================================
    companion object {
        const val USER = "user"
    }
    //================================================

    private inner class Wrapper(
        val users: ArrayList<User>,
        val comments: ArrayList<Comment>
    )

    // 02.09.21
    private fun fetchData() {
        progressBar.visibility = View.VISIBLE

        Observable.zip(RetrofitClient.getUsers(),
            RetrofitClient.getComments(), object : BiFunction<Users, Comments, Wrapper> {
                override fun apply(t1: Users, t2: Comments): Wrapper {
                    return Wrapper(t1.data, t2.data)
                }

            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Wrapper> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Wrapper) {
                    hideProgress()
                    userAdapter.setData(t.users)
                    commentAdapter.setData(t.comments)

                    // и так и по версии наверху работает, нужно бы разобраться

                  /*  setUsers(t.users)
                    setComments(t.comments)*/
                }

                override fun onError(e: Throwable) {
                    hideProgress()
                    println(e)
                }

                override fun onComplete() {
                    hideProgress()
                }

            })
    }

    // 01.09.21

    /* RetrofitClient.getUsers()
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe(object : Observer<Users> {
             override fun onSubscribe(d: Disposable) {
             }

             override fun onNext(t: Users) {
                 setUsers(t.data)
             }

             override fun onError(e: Throwable) {
                 hideProgress()
                 println(e)
             }

             override fun onComplete() {
                 hideProgress()
             }
         })*/

    private fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    private fun setUsers(list: ArrayList<User>) {
        userAdapter.setData(list)
    }

    private fun setComments(list: ArrayList<Comment>) {
        commentAdapter.setData(list)
    }
}