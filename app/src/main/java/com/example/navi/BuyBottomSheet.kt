package com.example.navi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.navi.data.AppDatabase
import com.example.navi.data.Ticket
import com.example.navi.ui.FilmForm
import com.example.navi.ui.LoginForm
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BuyBottomSheet() : BottomSheetDialogFragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.activity_buy_bottom_sheet, container, false)
        val buyButton = view.findViewById<TextView>(R.id.BuyButton)

        // 1. Get the database instance
        val db = AppDatabase.getDatabase(requireContext())

        buyButton.setOnClickListener {
            // 2. Create a new ticket object
            val ticket = Ticket(
                userId = 1,
                filmId = 1
            )

            CoroutineScope(Dispatchers.IO).launch {
                // 3. Insert the ticket into the database
                db.TicketDao().insertTicket(ticket)
            }
        }



        return view
    }
}