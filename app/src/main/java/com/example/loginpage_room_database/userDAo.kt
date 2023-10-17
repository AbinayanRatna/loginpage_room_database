package com.example.loginpage_room_database

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface userDAo {
    @Query("Select * FROM User_table")
    fun getAll(): List<userClass>

    @Query("Select * FROM User_table WHERE user_name LIKE:user_name")
    suspend fun findByUsername(user_name: String): userClass

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: userClass): Long

    @Delete
    suspend fun delete(user: userClass)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Transaction
    suspend fun insertOrUpdate(user: userClass, Context: Context) {
        val id = insert(user)
        if (id == -1L) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(Context, "Username is already on use !", Toast.LENGTH_SHORT).show()
            }
        } else {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(Context, "Account added successfully.", Toast.LENGTH_SHORT).show()

            }
        }
    }
}

