package com.example.navi.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.navi.ui.MainView
import com.example.navi.R
import com.example.navi.RegisterForm
import com.example.navi.data.AppDatabase
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginForm : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_form)
        val db = AppDatabase.getDatabase(this)
        val checkbox = findViewById<CheckBox>(R.id.checkbox_terms)
        val btnLogin = findViewById<TextView>(R.id.btnLogin)
        val btnToRegister = findViewById<TextView>(R.id.btnToRegister)
        val etUsername = findViewById<TextInputEditText>(R.id.Username)
        val etEmail = findViewById<TextInputEditText>(R.id.tvEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.Password)


        val text = "I agree the Term of use and Condition"
        val spannable = SpannableString(text)


        val termStart = text.indexOf("Term of use")
        val termEnd = termStart + "Term of use".length
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#90DAFF")),
            termStart,
            termEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        val condStart = text.indexOf("Condition")
        val condEnd = condStart + "Condition".length
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#90DAFF")),
            condStart,
            condEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        checkbox.text = spannable

        btnToRegister.setOnClickListener {
            val intent = Intent(this, RegisterForm::class.java)
            startActivity(intent)
        };
        btnLogin.setOnClickListener {
            if(etUsername.text.isNullOrEmpty() || etPassword.text.isNullOrEmpty()){
                Toast.makeText(this, "Missing Information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            lifecycleScope.launch {

                val user = db.userDao().login(
                    etUsername.text.toString(),
                    etPassword.text.toString()
                )

                if (user != null) {

                    startActivity(Intent(this@LoginForm, MainView::class.java))
                    finish()

                } else {

                    Toast.makeText(
                        this@LoginForm,
                        "Username atau password salah",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }
        }
    }

}