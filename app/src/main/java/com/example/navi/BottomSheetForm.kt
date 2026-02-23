package com.example.navi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.navi.ui.FilmForm
import com.example.navi.ui.LoginForm
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilmBottomSheet() : BottomSheetDialogFragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.activity_bottom_sheet_form, container, false)

        val btnForm = view.findViewById<TextView>(R.id.btnForm)
        val btnLogOut = view.findViewById<TextView>(R.id.btnLogOut)

        btnForm.setOnClickListener {
            val intent = Intent(requireContext(), FilmForm::class.java)
            startActivity(intent)
        }
        btnLogOut.setOnClickListener {
            val intent = Intent(requireContext(), LoginForm::class.java)
            startActivity(intent)
        }

        return view
    }
}