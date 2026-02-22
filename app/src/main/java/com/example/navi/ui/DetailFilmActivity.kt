package com.example.navi.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.navi.R
import com.example.navi.data.AppDatabase
import com.example.navi.data.FilmDao
import kotlinx.coroutines.launch

class DetailFilmActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var filmDao: FilmDao
    private var filmId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_film)
        val db = AppDatabase.getDatabase(applicationContext)
        filmDao = db.filmDao()

        filmId = intent.getIntExtra("filmId", 0)

        val btnDelete = findViewById<Button>(R.id.btnDelete)

        btnDelete.setOnClickListener {
            lifecycleScope.launch {
                filmDao.deleteById(filmId)
                finish()
            }
        }

        val imgPoster = findViewById<ImageView>(R.id.imgPosterDetail)
        val imgBackground = findViewById<ImageView>(R.id.imgPosterBackground)
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
        tvDurasi.text = "$durasi Minutes"
        tvHarga.text = "Rp $harga"

        if (!poster.isNullOrEmpty()) {
            val uri = Uri.parse(poster)
            imgPoster.setImageURI(uri)
            imgBackground.setImageURI(uri)
        }

    }

}