package com.example.navi


import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.navi.data.Film

class FilmAdapter(
    private val listFilm: List<Film>,
    private val onDeleteClick: (Film) -> Unit
) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    inner class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
        val tvJudul: TextView = itemView.findViewById(R.id.tvJudul)
        val tvGenre: TextView = itemView.findViewById(R.id.tvGenre)

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



        holder.btnDelete.setOnClickListener {
            onDeleteClick(film)
        }
    }

    override fun getItemCount(): Int = listFilm.size
}