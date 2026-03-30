package com.example.navi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.navi.data.AppDatabase
import com.example.navi.data.FilmDao
import com.example.navi.data.TheaterDao
import com.example.navi.ui.FilmAdapter

class TheaterFragment : Fragment(R.layout.activity_theater_fragment) {
    private lateinit var theaterDao: TheaterDao
    private lateinit var recyclerFilm: RecyclerView
    private lateinit var adapter: FilmAdapter
    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btnDetail = view.findViewById<ImageView>(R.id.btnMenu)

        btnDetail.setOnClickListener {

            val bottomSheet = FilmBottomSheet()
            bottomSheet.show(parentFragmentManager, "FilmBottomSheet")

        }

        val db = AppDatabase.getDatabase(requireContext())
        theaterDao = db.TheaterDao()

    }
}