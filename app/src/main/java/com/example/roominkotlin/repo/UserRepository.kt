package com.example.roominkotlin.repo

import androidx.lifecycle.LiveData
import com.example.roominkotlin.model.User
import com.example.roominkotlin.model.UserDao

class UserRepository(private val userDao: UserDao) {



    fun readAllData():LiveData<List<User>>
    {
        return  userDao.readAlldata()
    }

    val readallData:LiveData<List<User>> = userDao.readAlldata()


    suspend fun insert(user: User)
    {
        userDao.insert(user)
    }

    suspend fun updateUser(user: User)
    {
        userDao.upadteUser(user)
    }

    suspend fun deleteUser(user: User)
    {
        userDao.deleteUser(user)
        user.id==0
    }

    suspend fun deleteAllUser(){
        userDao.deleteAllUsers()
    }
}