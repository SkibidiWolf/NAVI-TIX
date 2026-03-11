package com.example.navi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.navi.data.AppDatabase
import com.example.navi.data.User
import com.example.navi.ui.LoginForm
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class RegisterForm : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_form)

        val Username = findViewById<TextInputEditText>(R.id.Username)
        val Email  = findViewById<TextInputEditText>(R.id.tvEmail)
        val btnRegister = findViewById<TextView>(R.id.btnRegister)
        val Password = findViewById<TextInputEditText>(R.id.Password)
        val db = AppDatabase.getDatabase(this)
        btnRegister.setOnClickListener {
            if(Username.text.isNullOrEmpty() || Email.text.isNullOrEmpty() || Password.text.isNullOrEmpty()){
                Toast.makeText(this, "Missing Information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {



                val user = User(
                    username = Username.text.toString(),
                    email = Email.text.toString(),
                    password = Password.text.toString()
                )


                db.userDao().insertUser(user)

                Toast.makeText(this@RegisterForm, "Register complete", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@RegisterForm, LoginForm::class.java)
                startActivity(intent)

                finish()

            }
        }
    }
}