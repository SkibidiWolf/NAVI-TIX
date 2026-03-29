package com.example.navi.data

data class TicketWithFilm(
    val ticketId: Int,
    val filmId: Int,
    val judul: String,
    val posterUri: String,
    val purchaseDate: Long
)