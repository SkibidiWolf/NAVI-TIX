package com.example.navi.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TheaterDao {

    @Insert
    suspend fun insertTheater(theater: Theater)

    @Update
    suspend fun updateTheater(theater: Theater)

    @Delete
    suspend fun deleteTheater(theater: Theater)

    @Query("SELECT * FROM theater")
    suspend fun getAllTheater(): List<Theater>

    @Query("SELECT * FROM theater WHERE idTheater = :id")
    suspend fun getTheaterById(id: Int): Theater?

    @Query("DELETE FROM theater WHERE idTheater = :id")
    suspend fun deleteById(id: Int)

}