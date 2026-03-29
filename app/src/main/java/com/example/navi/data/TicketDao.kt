package com.example.navi.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TicketDao {

    @Insert
    suspend fun insertTicket(ticket: Ticket)

    @Query("SELECT * FROM ticket WHERE userId = :userId")
    suspend fun getTicketsByUser(userId: Int): List<Ticket>

    @Query("""
    SELECT 
        ticket.id AS ticketId,
        ticket.filmId AS filmId, 
        film.judul,
        film.posterUri,
        ticket.purchaseDate
    FROM ticket
    INNER JOIN film ON ticket.filmId = film.idFilm
    WHERE ticket.userId = :userId
""")
    suspend fun getTicketsWithFilm(userId: Int): List<TicketWithFilm>
}