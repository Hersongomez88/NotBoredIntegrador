package com.example.notboredintegrador

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notboredintegrador.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTermsAndCond.setOnClickListener {
            navigate(TermsAndConditions())
        }
    }

    private fun navigate(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}