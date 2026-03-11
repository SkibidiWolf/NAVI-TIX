package com.example.navi

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment(R.layout.activity_profile_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
        val btnChangePhoto = view.findViewById<Button>(R.id.btnChangePhoto)

        // contoh username sementara
        tvUsername.text = "Ryo"

        btnChangePhoto.setOnClickListener {
            Toast.makeText(requireContext(),"Fitur ganti foto belum dibuat",Toast.LENGTH_SHORT).show()
        }
    }
}