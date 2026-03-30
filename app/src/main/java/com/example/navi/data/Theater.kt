package com.example.navi.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theater")
data class Theater(
    @PrimaryKey(autoGenerate = true)
    val idTheater: Int = 0,
    val namaTheater: String,
    val fotoUri: String,
    val lokasi: String
)