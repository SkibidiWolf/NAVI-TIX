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
import com.example.navi.data.Theater

class TheaterAdapter : RecyclerView.Adapter<TheaterAdapter.TheaterViewHolder>() {

    private var listTheater = listOf<Theater>()

    class TheaterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama = itemView.findViewById<TextView>(R.id.tvNamaTheater)
        val lokasi = itemView.findViewById<TextView>(R.id.tvLokasi)
        val foto = itemView.findViewById<ImageView>(R.id.imgTheater)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheaterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_theater, parent, false)
        return TheaterViewHolder(view)
    }

    override fun onBindViewHolder(holder: TheaterViewHolder, position: Int) {
        val theater = listTheater[position]

        holder.nama.text = theater.namaTheater
        holder.lokasi.text = theater.lokasi

        // kalau pakai URI gambar
        holder.foto.setImageURI(Uri.parse(theater.fotoUri))
    }

    override fun getItemCount(): Int = listTheater.size

    fun setData(newList: List<Theater>) {
        listTheater = newList
        notifyDataSetChanged()
    }
}