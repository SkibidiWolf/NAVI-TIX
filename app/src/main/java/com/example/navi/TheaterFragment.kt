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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navi.data.AppDatabase
import com.example.navi.data.FilmDao
import com.example.navi.data.TheaterDao
import com.example.navi.ui.FilmAdapter
import com.example.navi.ui.TheaterAdapter
import kotlinx.coroutines.launch

class TheaterFragment : Fragment(R.layout.activity_theater_fragment) {
    private lateinit var theaterDao: TheaterDao
    private lateinit var recyclerTheater: RecyclerView
    private lateinit var adapter: TheaterAdapter
    private lateinit var db: AppDatabase
    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btnDetail = view.findViewById<ImageView>(R.id.btnMenu)

        btnDetail.setOnClickListener {

            val bottomSheet = FilmBottomSheet()
            bottomSheet.show(parentFragmentManager, "FilmBottomSheet")

        }

        db = AppDatabase.getDatabase(requireContext())
        theaterDao = db.TheaterDao()

        adapter = TheaterAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerTheater)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ambil data dari Room
        lifecycleScope.launch {
            val data = db.TheaterDao().getAllTheater()
            adapter.setData(data)
        }

    }
}
