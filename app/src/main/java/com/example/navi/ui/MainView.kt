package com.example.navi.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        val btnForm = findViewById<Button>(R.id.btnForm)

        btnForm.setOnClickListener { val intent = Intent(this, FilmForm::class.java)
            startActivity(intent)  };

        val db = AppDatabase.Companion.getDatabase(this)
        filmDao = db.filmDao()



        recyclerFilm = findViewById(R.id.recyclerFilm)
        recyclerFilm.layoutManager = GridLayoutManager(this, 2)

        loadData()
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
                    intent.putExtra("poster", film.posterUri)

                    startActivity(intent)
                }
            )
                }
    }
}