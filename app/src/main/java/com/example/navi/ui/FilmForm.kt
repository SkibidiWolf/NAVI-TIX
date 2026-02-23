package com.example.navi.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.example.navi.R
import com.example.navi.data.AppDatabase
import com.example.navi.data.Film
import com.example.navi.data.FilmDao
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class FilmForm : AppCompatActivity() {

    private lateinit var imgPreview: ImageView
    private var selectedImageUri: Uri? = null

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            if (uri != null) {
                selectedImageUri = uri
                imgPreview.setImageURI(uri)

                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }
        }

    private lateinit var filmDao: FilmDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_film_form)
        val db = AppDatabase.Companion.getDatabase(applicationContext)
        filmDao = db.filmDao()

        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener { finish() }

        val btnPilihGambar = findViewById<CardView>(R.id.btnPilihGambar)
        imgPreview = findViewById(R.id.imgPreview)

        val etJudul = findViewById<TextInputEditText>(R.id.etJudul)
        val etGenre = findViewById<TextInputEditText>(R.id.etGenre)
        val etDurasi = findViewById<TextInputEditText>(R.id.etDurasi)
        val etDirector = findViewById<TextInputEditText>(R.id.etDirector)
        val etSynopsis = findViewById<TextInputEditText>(R.id.etSynopsis)
        val etJadwal = findViewById<TextInputEditText>(R.id.etJadwal)
        val etHarga = findViewById<TextInputEditText>(R.id.etHarga)


        val btnSave = findViewById<TextView>(R.id.btnSave)

        btnPilihGambar.setOnClickListener {
            pickImage.launch(arrayOf("image/*"))
        }

        btnSave.setOnClickListener {

            val judul = etJudul.text.toString()
            val genre = etGenre.text.toString()
            val durasi = etDurasi.text.toString().toIntOrNull() ?: 0
            val director = etDirector.text.toString()
            val synopsis = etSynopsis.text.toString()
            val harga = etHarga.text.toString().toIntOrNull() ?: 0
            val tanggal = etJadwal.text.toString()
            val poster = selectedImageUri?.toString() ?: ""



            val film = Film(
                judul = judul,
                genre = genre,
                durasi = durasi,
                director = director,
                synopsis = synopsis,
                jadwaltayang = tanggal,
                hargaTiket = harga,
                posterUri = poster
            )
            lifecycleScope.launch {
                filmDao.insertFilm(film)
            }
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()

        }

    }
}