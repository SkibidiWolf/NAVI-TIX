package com.example.navi.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FilmDao {

    @Insert
    suspend fun insertFilm(film: Film)

    @Update
    suspend fun updateFilm(film: Film)

    @Delete
    suspend fun deleteFilm(film: Film)

    @Query("SELECT * FROM film")
    suspend fun getAllFilm(): List<Film>

    @Query("SELECT * FROM film WHERE idFilm = :id")
    suspend fun getFilmById(id: Int): Film?

    @Query("DELETE FROM film WHERE idFilm = :id")
    suspend fun deleteById(id: Int)
}
