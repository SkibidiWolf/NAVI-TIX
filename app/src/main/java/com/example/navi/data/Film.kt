package com.example.navi.data

import androidx.room.PrimaryKey

@Entity(tableName = "film")
data class Film(
    @PrimaryKey(autoGenerate = true)
    val idFilm: Int = 0,
    val judul: String,
    val genre: String,
    val durasi: Int,
    val hargaTiket: Int
)
