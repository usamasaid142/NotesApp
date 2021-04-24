package com.example.roominkotlin.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("select * from user_table ORDER BY id ASC")
    fun readAlldata():LiveData<List<User>>

    @Update
    suspend fun upadteUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("delete from user_table")
    suspend fun deleteAllUsers()
}