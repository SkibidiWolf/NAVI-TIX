package com.example.navi.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.view.View
import com.bumptech.glide.Glide
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat.enableEdgeToEdge
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navi.FilmBottomSheet
import com.example.navi.R
import com.example.navi.data.AppDatabase
import com.example.navi.data.FilmDao
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.activity_main_view) {
    private lateinit var filmDao: FilmDao
    private lateinit var recyclerFilm: RecyclerView
    private lateinit var adapter: FilmAdapter
    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val db = AppDatabase.getDatabase(requireContext())
        filmDao = db.filmDao()

        //promo iklan
        val promoOverlay = view.findViewById<LinearLayout>(R.id.promoOverlay)
        val imgPromoPoster = view.findViewById<ImageView>(R.id.imgPromoPoster)
        val tvPromoTitle = view.findViewById<TextView>(R.id.tvPromoTitle)
        val tvPromoGenre = view.findViewById<TextView>(R.id.tvGenre)
        val btnPromoClose = view.findViewById<ImageView>(R.id.btnPromoClose)
        val btnPromoBuy = view.findViewById<TextView>(R.id.btnPromoBuy)
        val searchView = view.findViewById<SearchView>(R.id.searchView)



        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return true
            }
        })




        lifecycleScope.launch {

            val film = filmDao.getRandomFilm()

            if (film != null) {

                promoOverlay.visibility = View.VISIBLE
                tvPromoTitle.text = film.judul
                tvPromoGenre.text = film.genre

                Glide.with(requireContext())
                    .load(film.posterUri)
                    .into(imgPromoPoster)

                btnPromoBuy.setOnClickListener {
                    val intent = Intent(requireContext(), DetailFilmActivity::class.java)



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







        recyclerFilm = view.findViewById(R.id.recyclerFilm)
        recyclerFilm.layoutManager = GridLayoutManager(requireContext(), 2)

        loadData()

        val btnDetail = view.findViewById<ImageView>(R.id.btnMenu)

        btnDetail.setOnClickListener {

            val bottomSheet = FilmBottomSheet()
            bottomSheet.show(parentFragmentManager, "FilmBottomSheet")

        }

    }


        override fun onResume() {
            super.onResume()
            loadData()
        }

        private fun loadData() {

            viewLifecycleOwner.lifecycleScope.launch {

                val listFilm = filmDao.getAllFilm()

                adapter = FilmAdapter(
                    listFilm = listFilm,
                    onItemClick = { film ->

                        val intent = Intent(requireContext(), DetailFilmActivity::class.java)

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

                recyclerFilm.adapter = adapter
            }
        }
    }
