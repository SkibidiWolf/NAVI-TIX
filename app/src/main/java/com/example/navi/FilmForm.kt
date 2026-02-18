package com.example.navi

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private lateinit var imgPreview: ImageView
private var selectedImageUri: Uri? = null


class FilmForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_film_form)
        val btnPilihGambar = findViewById<Button>(R.id.btnPilihGambar)
        imgPreview = findViewById(R.id.imgPreview)

        btnPilihGambar.setOnClickListener {
            pickImage.launch("image/*")
        }

    }

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                selectedImageUri = uri
                imgPreview.setImageURI(uri)
            }
        }
}

