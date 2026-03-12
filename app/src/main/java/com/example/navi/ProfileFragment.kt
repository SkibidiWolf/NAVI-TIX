package com.example.navi

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.navi.data.AppDatabase
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.activity_profile_fragment) {

    private lateinit var imgProfile: ImageView
    private lateinit var tvUsername: TextView
    private lateinit var tvId: TextView
    private lateinit var db: AppDatabase
    private var selectedImageUri: Uri? = null
    private var userId: Int = -1

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->

                if(uri != null){

                    selectedImageUri = uri   // simpan sementara
                    imgProfile.setImageURI(uri) // preview foto

                    }


            }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        imgProfile = view.findViewById(R.id.imgProfile)
        tvUsername = view.findViewById(R.id.tvUsername)
        tvId = view.findViewById(R.id.tvId)
        val btnChange = view.findViewById<Button>(R.id.btnChangePhoto)
        val btnSave = view.findViewById<Button>(R.id.btnSavePhoto)

        db = AppDatabase.getDatabase(requireContext())

        val sharedPref = requireActivity()
            .getSharedPreferences("user_session", AppCompatActivity.MODE_PRIVATE)

        userId = sharedPref.getInt("userId", -1)

        // load user
        viewLifecycleOwner.lifecycleScope.launch {

            val user = db.userDao().getUserById(userId)

            if (user != null) {

                tvUsername.text = user.username
                tvId.text = "ID : ${user.id}"


                if (user.profilePhoto != null) {

                    Glide.with(requireContext())
                        .load(user.profilePhoto)
                        .into(imgProfile)
                }
            }
        }

        // buka galeri
        btnChange.setOnClickListener {

            pickImage.launch("image/*")

        }

        // simpan foto
        btnSave.setOnClickListener {

            if (selectedImageUri != null) {

                viewLifecycleOwner.lifecycleScope.launch {

                    db.userDao().updatePhoto(userId, selectedImageUri.toString())

                    Toast.makeText(requireContext(), "Photo saved", Toast.LENGTH_SHORT).show()

                }

            } else {

                Toast.makeText(requireContext(), "Choose a photo first", Toast.LENGTH_SHORT).show()

            }
        }
    }
}

