package com.example.navi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.navi.data.TicketWithFilm
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.navi.data.AppDatabase
import com.example.navi.data.Ticket
import com.example.navi.ui.FilmForm
import com.example.navi.ui.LoginForm
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BuyBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.activity_buy_bottom_sheet, container, false)
        val buyButton = view.findViewById<TextView>(R.id.BuyButton)

        val db = AppDatabase.getDatabase(requireContext())

        // 🔹 Ambil userId dari session
        val sharedPref = requireActivity()
            .getSharedPreferences("user_session", AppCompatActivity.MODE_PRIVATE)

        val userId = sharedPref.getInt("userId", -1)

        // 🔹 Ambil filmId dari arguments
        val filmId = arguments?.getInt("filmId") ?: -1

        buyButton.setOnClickListener {
            println("DEBUG userId: $userId")
            println("DEBUG filmId: $filmId")



            if (userId == -1 || filmId == -1) {
                Toast.makeText(requireContext(), "Invalid data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ticket = Ticket(
                userId = userId,
                filmId = filmId
            )

            viewLifecycleOwner.lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    db.TicketDao().insertTicket(ticket)
                }

                Toast.makeText(requireContext(), "Ticket berhasil dibeli", Toast.LENGTH_SHORT).show()

                dismiss() // tutup bottom sheet
            }
        }

        return view
    }
}