package com.example.navi.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUser(username: String): User?

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    suspend fun login(username: String, password: String): User?

    @Query("UPDATE Users SET profilePhoto = :uri WHERE id = :userId")
    suspend fun updatePhoto(userId: Int, uri: String)

    @Query("SELECT * FROM Users WHERE id = :id")
    suspend fun getUserById(id: Int): User

}