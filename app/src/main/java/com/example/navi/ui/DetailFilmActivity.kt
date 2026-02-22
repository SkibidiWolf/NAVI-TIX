package com.example.navi.ui

import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.navi.R

class DetailFilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_film)

        val imgPoster = findViewById<ImageView>(R.id.imgPosterDetail)
        val tvJudul = findViewById<TextView>(R.id.tvJudulDetail)
        val tvGenre = findViewById<TextView>(R.id.tvGenreDetail)
        val tvDurasi = findViewById<TextView>(R.id.tvDurasiDetail)
        val tvHarga = findViewById<TextView>(R.id.tvHargaDetail)

        // Ambil data dari intent
        val judul = intent.getStringExtra("judul")
        val genre = intent.getStringExtra("genre")
        val durasi = intent.getIntExtra("durasi", 0)
        val harga = intent.getIntExtra("harga", 0)
        val poster = intent.getStringExtra("poster")

        tvJudul.text = judul
        tvGenre.text = genre
        tvDurasi.text = "Durasi: $durasi menit"
        tvHarga.text = "Harga: Rp $harga"

        if (!poster.isNullOrEmpty()) {
            imgPoster.setImageURI(Uri.parse(poster))
        }
    }

}