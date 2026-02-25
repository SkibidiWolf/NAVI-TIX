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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.launch

class DetailFilmActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var filmDao: FilmDao
    private var filmId: Int = 0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_film)
        val db = AppDatabase.getDatabase(applicationContext)
        filmDao = db.filmDao()

        filmId = intent.getIntExtra("filmId", 0)

        val btnDelete = findViewById<TextView>(R.id.btnDelete)

        btnDelete.setOnClickListener {
            lifecycleScope.launch {
                filmDao.deleteById(filmId)
                finish()
            }
        }

        val imgPoster = findViewById<ImageView>(R.id.imgPosterDetail)
        val imgBackground = findViewById<ImageView>(R.id.imgPosterBackground)
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val tvJudul = findViewById<TextView>(R.id.tvJudulDetail)
        val tvGenre = findViewById<TextView>(R.id.tvGenreDetail)
        val tvDurasi = findViewById<TextView>(R.id.tvDurasiDetail)
        val tvHarga = findViewById<TextView>(R.id.tvHargaDetail)
        val tvDirector = findViewById<TextView>(R.id.tvDirectorDetail)
        val tvSynopsis = findViewById<TextView>(R.id.tvSynopsisDetail)
        val tvRelease = findViewById<TextView>(R.id.tvReleaseDetail)
        val tvDescRelease = findViewById<TextView>(R.id.tvDescReleaseDetail)
        val tvDescJudul = findViewById<TextView>(R.id.tvDescJudulDetail)
        val tvDescDirector = findViewById<TextView>(R.id.tvDescDirectorDetail)

        btnBack.setOnClickListener { finish() }

        // Ambil data dari intent
        val judul = intent.getStringExtra("judul")
        val genre = intent.getStringExtra("genre")
        val durasi = intent.getIntExtra("durasi", 0)
        val director = intent.getStringExtra("director")
        val synopsis = intent.getStringExtra("synopsis")
        val release = intent.getStringExtra("tanggal")
        val harga = intent.getIntExtra("harga", 0)
        val poster = intent.getStringExtra("poster")
        val trailerId = intent.getStringExtra("TRAILER_ID")

        tvJudul.text = judul
        tvDescJudul.text = judul
        tvGenre.text = genre
        tvDurasi.text = "$durasi Minutes"
        tvDirector.text = director
        tvDescDirector.text = director
        tvSynopsis.text = synopsis
        tvRelease.text = release
        tvDescRelease.text = release
        tvHarga.text = "Rp $harga"

        if (!poster.isNullOrEmpty()) {
            val uri = Uri.parse(poster)
            imgPoster.setImageURI(uri)
            imgBackground.setImageURI(uri)
        }

        val youtubePlayerView = findViewById<YouTubePlayerView>(R.id.youtubePlayer)
        lifecycle.addObserver(youtubePlayerView)

        youtubePlayerView.enableAutomaticInitialization = false

        youtubePlayerView.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo("dQw4w9WgXcQ", 0f)
            }
        }, true)

    }

}
