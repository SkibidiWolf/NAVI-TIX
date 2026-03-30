package com.example.navi

import android.annotation.SuppressLint
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
import com.example.navi.data.AppDatabase
import com.example.navi.data.Theater
import com.example.navi.data.TheaterDao
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class TheaterForm : AppCompatActivity() {
    private lateinit var imgTheaterPreview: ImageView
    private lateinit var theaterDao: TheaterDao
    private var selectedImageUri: Uri? = null

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            if (uri != null) {
                selectedImageUri = uri
                imgTheaterPreview.setImageURI(uri)

                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }
        }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_theater_form)
        
        val db = AppDatabase.getDatabase(applicationContext)
        theaterDao = db.TheaterDao()

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        val btnPilihGambar = findViewById<CardView>(R.id.btnPilihGambar)
        imgTheaterPreview = findViewById(R.id.imgTheaterPreview)

        val etTheaterName = findViewById<TextInputEditText>(R.id.etTheaterName)
        val etLokasi = findViewById<TextInputEditText>(R.id.etLokasi)
        val btnSave = findViewById<TextView>(R.id.btnSave)

        btnPilihGambar.setOnClickListener {
            pickImage.launch(arrayOf("image/*"))
        }

        btnSave.setOnClickListener {
            val nama = etTheaterName.text.toString()
            val lokasi = etLokasi.text.toString()
            val foto = selectedImageUri?.toString() ?: ""

            if (nama.isEmpty() || lokasi.isEmpty()) {
                Toast.makeText(this, "Tolong Lengkapi Data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val theater = Theater(
                namaTheater = nama,
                lokasi = lokasi,
                fotoUri = foto,
            )
            
            lifecycleScope.launch {
                theaterDao.insertTheater(theater)
                Toast.makeText(this@TheaterForm, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}