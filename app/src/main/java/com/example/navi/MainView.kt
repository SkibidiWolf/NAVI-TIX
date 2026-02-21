package com.example.navi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val db = AppDatabase.getDatabase(this)
        filmDao = db.filmDao()

        recyclerFilm = findViewById(R.id.recyclerFilm)
        recyclerFilm.layoutManager = LinearLayoutManager(this)

        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()   // 🔥 reload setiap kembali ke halaman ini
    }

    private fun loadData() {
        lifecycleScope.launch {
            val listFilm = filmDao.getAllFilm()
            recyclerFilm.adapter = FilmAdapter(listFilm) { film ->
                lifecycleScope.launch {
                    filmDao.deleteFilm(film)
                    loadData() // reload setelah delete
                }
            }
        }
    }
}