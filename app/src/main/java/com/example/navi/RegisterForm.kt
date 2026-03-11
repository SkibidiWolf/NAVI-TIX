package com.example.navi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.navi.data.AppDatabase
import com.example.navi.data.User
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class RegisterForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_form)

        val etUsername = findViewById<TextInputEditText>(R.id.Username)
        val etEmail  = findViewById<TextInputEditText>(R.id.tvEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.Password)
        val db = AppDatabase.getDatabase(this)
        lifecycleScope.launch {

            val user = User(
                username = etUsername.text.toString(),
                email = etEmail.text.toString(),
                password = etPassword.text.toString()
            )

            db.userDao().insertUser(user)

            Toast.makeText(this@RegisterForm, "Register berhasil", Toast.LENGTH_SHORT).show()

        }
    }
}