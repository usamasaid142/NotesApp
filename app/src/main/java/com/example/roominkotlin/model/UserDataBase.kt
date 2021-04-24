package com.example.roominkotlin.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var Instance: UserDataBase?= null

        fun getDatabase(context: Context):UserDataBase{
            val teminstance= Instance
            if (teminstance!= null) {
                return teminstance
            }
            synchronized(this) {

                val instance=Room.databaseBuilder(context.applicationContext,UserDataBase::class.java,"database")
                    .build()

                Instance=instance
                return instance

            }

        }
    }
}