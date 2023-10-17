package com.example.loginpage_room_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_table")
data class userClass(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "password") val password: String?
)

