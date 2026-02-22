package com.example.navi.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.navi.R
import com.example.navi.data.Film

class FilmAdapter(
    private val listFilm: List<Film>,
    private val onDeleteClick: (Film) -> Unit
) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    inner class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJudul: TextView = itemView.findViewById(R.id.tvJudul)
        val tvGenre: TextView = itemView.findViewById(R.id.tvGenre)
        val imgPoster: ImageView = itemView.findViewById(R.id.imgPoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = listFilm[position]

        holder.tvJudul.text = film.judul
        holder.tvGenre.text = film.genre

        if (!film.posterUri.isNullOrEmpty()) {
            try {
                holder.imgPoster.setImageURI(Uri.parse(film.posterUri))
            } catch (e: Exception) {
                holder.imgPoster.setImageResource(R.drawable.ic_launcher_background)
            }
        }


    }

    override fun getItemCount(): Int = listFilm.size
}