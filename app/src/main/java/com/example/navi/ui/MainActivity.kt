package com.example.navi.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.navi.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val logo = findViewById<ImageView>(R.id.IMGLogo)

        // animasi fade in
        logo.alpha = 0f
        logo.animate()
            .alpha(1f)
            .setDuration(1000)
            .withEndAction {
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, LoginForm::class.java))
                    overridePendingTransition(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                    finish()

                }, 1500) // delay 0.5 detik


            }
    }
}