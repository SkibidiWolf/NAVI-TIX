package com.example.navi.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "ticket",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Film::class,
            parentColumns = ["idFilm"],
            childColumns = ["filmId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Ticket(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val filmId: Int,
    val purchaseDate: Long = System.currentTimeMillis()
)