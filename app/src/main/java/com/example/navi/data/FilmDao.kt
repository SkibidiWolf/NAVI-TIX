package com.example.navi.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FilmDao {

    @Insert
    fun insertFilm(film: Film)

    @Update
    fun updateFilm(film: Film)

    @Delete
    fun deleteFilm(film: Film)

    @Query("SELECT * FROM film")
    fun getAllFilm(): List<Film>
}
