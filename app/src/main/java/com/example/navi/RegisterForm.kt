package com.example.navi

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
        val checkbox = findViewById<CheckBox>(R.id.checkbox_terms)

        // checkbox messages

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


        val db = AppDatabase.getDatabase(this)
        btnRegister.setOnClickListener {

            val username = Username.text.toString()
            val email = Email.text.toString()
            val password = Password.text.toString()

            if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Missing Information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {

                val existingUser = db.userDao().getUser(username)

                if(existingUser != null){
                    Toast.makeText(this@RegisterForm, "Username sudah dipakai", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                val user = User(
                    username = username,
                    email = email,
                    password = password
                )

                db.userDao().insertUser(user)

                Toast.makeText(this@RegisterForm, "Register complete", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this@RegisterForm, LoginForm::class.java))
                finish()
            }
        }
    }
}