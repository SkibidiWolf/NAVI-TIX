package com.example.navi.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.view.View
import com.bumptech.glide.Glide
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navi.FilmBottomSheet
import com.example.navi.R
import com.example.navi.data.AppDatabase
import com.example.navi.data.FilmDao
import kotlinx.coroutines.launch

class MainView : AppCompatActivity() {
    private lateinit var filmDao: FilmDao
    private lateinit var recyclerFilm: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_view)
        val db = AppDatabase.Companion.getDatabase(this)
        filmDao = db.filmDao()

        //promo iklan
        val promoOverlay = findViewById<LinearLayout>(R.id.promoOverlay)
        val imgPromoPoster = findViewById<ImageView>(R.id.imgPromoPoster)
        val tvPromoTitle = findViewById<TextView>(R.id.tvPromoTitle)
        val tvPromoGenre = findViewById<TextView>(R.id.tvGenre)
        val btnPromoClose = findViewById<ImageView>(R.id.btnPromoClose)
        val btnPromoBuy = findViewById<TextView>(R.id.btnPromoBuy)




        lifecycleScope.launch {

            val film = filmDao.getRandomFilm()

            if (film != null) {

                promoOverlay.visibility = View.VISIBLE
                tvPromoTitle.text = film.judul

                Glide.with(this@MainView)
                    .load(film.posterUri)
                    .into(imgPromoPoster)

                btnPromoBuy.setOnClickListener {

                    val intent = Intent(this@MainView, DetailFilmActivity::class.java)

                    intent.putExtra("filmId", film.idFilm)
                    intent.putExtra("judul", film.judul)
                    intent.putExtra("genre", film.genre)
                    intent.putExtra("durasi", film.durasi)
                    intent.putExtra("director", film.director)
                    intent.putExtra("synopsis", film.synopsis)
                    intent.putExtra("tanggal", film.jadwaltayang)
                    intent.putExtra("harga", film.hargaTiket)
                    intent.putExtra("poster", film.posterUri)
                    intent.putExtra("trailer", film.trailerId)

                    startActivity(intent)
                }
            }
        }

        btnPromoClose.setOnClickListener {
            promoOverlay.visibility = View.GONE
        }







        recyclerFilm = findViewById(R.id.recyclerFilm)
        recyclerFilm.layoutManager = GridLayoutManager(this, 2)

        loadData()

        val btnDetail = findViewById<ImageView>(R.id.btnMenu)

        btnDetail.setOnClickListener {

            val bottomSheet = FilmBottomSheet()
            bottomSheet.show(supportFragmentManager, "FilmBottomSheet")

        }
    }

    override fun onResume() {
        super.onResume()
        loadData()   // 🔥 reload setiap kembali ke halaman ini
    }

    private fun loadData() {
        lifecycleScope.launch {
            val listFilm = filmDao.getAllFilm()
            recyclerFilm.adapter = FilmAdapter(
                listFilm = listFilm,
                onItemClick = { film ->
                    // aksi klik
                    val intent = Intent(this@MainView, DetailFilmActivity::class.java)
                    intent.putExtra("filmId", film.idFilm)
                    intent.putExtra("judul", film.judul)
                    intent.putExtra("genre", film.genre)
                    intent.putExtra("durasi", film.durasi)
                    intent.putExtra("director", film.director)
                    intent.putExtra("synopsis", film.synopsis)
                    intent.putExtra("tanggal", film.jadwaltayang)
                    intent.putExtra("harga", film.hargaTiket)
                    intent.putExtra("poster", film.posterUri)
                    intent.putExtra("trailer", film.trailerId)


                    startActivity(intent)
                }
            )
                }
    }
}