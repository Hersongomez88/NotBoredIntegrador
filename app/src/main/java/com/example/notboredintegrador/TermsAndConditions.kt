package com.example.notboredintegrador

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class TermsAndConditions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_terms_and_conditions)

        val ivClose = findViewById<ImageView>(R.id.iv_close)
        ivClose.setOnClickListener { onBackPressed() }

    }
}