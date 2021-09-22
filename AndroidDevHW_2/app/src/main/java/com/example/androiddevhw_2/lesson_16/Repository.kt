package com.example.androiddevhw_2.lesson_16

class Repository(private val dataSource: DataSource, private val params: Array<String>) {
    val users: List<Int>
        get() = dataSource.getRequest(params)

    fun postRequest(input:String):Int{
        return dataSource.postRequest(input)
    }
}