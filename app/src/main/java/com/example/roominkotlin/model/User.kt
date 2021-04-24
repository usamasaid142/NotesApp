package com.example.roominkotlin.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User (

    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val firstname:String,
    val lastname:String,
    val age:Int
) :Parcelable
