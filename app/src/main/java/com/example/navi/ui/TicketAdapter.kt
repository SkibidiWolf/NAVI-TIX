package com.example.navi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.navi.R
import com.example.navi.data.TicketWithFilm
import java.util.Date

class TicketAdapter : RecyclerView.Adapter<TicketAdapter.ViewHolder>() {

    private val list = mutableListOf<TicketWithFilm>()

    fun submitList(data: List<TicketWithFilm>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPoster: ImageView = view.findViewById(R.id.imgPoster)
        val tvJudul: TextView = view.findViewById(R.id.tvJudul)
        val tvTanggal: TextView = view.findViewById(R.id.tvTanggal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ticket, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.tvJudul.text = item.judul ?: "NO TITLE"

        // Format tanggal (opsional)
        val date = Date(item.purchaseDate)
        holder.tvTanggal.text = date.toString()

        // Load gambar (pakai Glide)
        Glide.with(holder.itemView.context)
            .load(item.posterUri)
            .into(holder.imgPoster)
    }
}