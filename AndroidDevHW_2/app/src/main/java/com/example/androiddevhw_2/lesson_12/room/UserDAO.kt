package com.example.androiddevhw_2.lesson_12.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    @Query("select * from users where id =:userId")
    fun findUserById(userId: Int): Flow<User>

    @Insert
    fun addUser(user: User)

    @Delete
    fun removeUser(user: User)

    @Query("delete from users where firstName =:firstName AND secondName =:lastName")
    fun deleteByNameAndLastName(firstName: String, lastName: String)

}