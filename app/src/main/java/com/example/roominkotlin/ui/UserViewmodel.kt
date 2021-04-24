package com.example.roominkotlin.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.roominkotlin.model.User
import com.example.roominkotlin.model.UserDataBase
import com.example.roominkotlin.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewmodel(application: Application) : AndroidViewModel(application) {

     val readAllData:LiveData<List<User>>
    private val repository:UserRepository

    init {
        val   userDao= UserDataBase.getDatabase(application).userDao()
        repository= UserRepository(userDao)
        readAllData=repository.readAllData()
    }

    fun addUser(user:User)
    {
        viewModelScope.launch (Dispatchers.IO){

            repository.insert(user)

        }
    }

    fun updateUser(user: User)
    {
        viewModelScope.launch(Dispatchers.IO) {

            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun deleteAllUser()
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUser()
        }
    }




}