package com.example.navi.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.navi.ui.MainView
import com.example.navi.R

class LoginForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_form)
        val checkbox = findViewById<CheckBox>(R.id.checkbox_terms)
        val btnLogin = findViewById<TextView>(R.id.btnLogin)




        val text = "I agree the Term of use and Condition"
        val spannable = SpannableString(text)


        val termStart = text.indexOf("Term of use")
        val termEnd = termStart + "Term of use".length
        spannable.setSpan(ForegroundColorSpan(Color.parseColor("#90DAFF")), termStart, termEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        val condStart = text.indexOf("Condition")
        val condEnd = condStart + "Condition".length
        spannable.setSpan(ForegroundColorSpan(Color.parseColor("#90DAFF")), condStart, condEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        checkbox.text = spannable

        btnLogin.setOnClickListener { val intent = Intent(this, MainView::class.java)
            startActivity(intent)  };
    }

}