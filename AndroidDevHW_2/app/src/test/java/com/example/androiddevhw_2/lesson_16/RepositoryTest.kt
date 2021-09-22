package com.example.androiddevhw_2.lesson_16

import junit.framework.TestCase
import org.junit.BeforeClass
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import java.lang.Exception

class RepositoryTest : TestCase() {

    private lateinit var dataSource: DataSource
    private lateinit var repository: Repository

    @BeforeClass
    public override fun setUp() {
        dataSource = object : DataSource {
            override fun postRequest(input: String): Int {
                val inputNumber = input.toInt()
                return inputNumber * 2
            }

            override fun getRequest(params: Array<String>): List<Int> {
                return params.map { it.toInt() }
            }
        }
        repository = Repository(dataSource, arrayOf("1", "1", "2", "3", "5"))
    }

    @Test
    fun testGetRequest() {
        assertEquals(repository.users, listOf(1, 1, 2, 3, 5))
    }

    @Test
    fun testGetRequestFail() {
        assertNotSame(repository.users, listOf(1, 1, 5, 3, 5))
    }

    @Test
    fun testPostRequest() {
        assertEquals(repository.postRequest("5"), 10)
    }

    @Test
    fun testPostRequestException() {
       assertThrows<Exception> { repository.postRequest(",.,") }
    }
}