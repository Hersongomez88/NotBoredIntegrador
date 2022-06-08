package com.example.notboredintegrador

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.example.notboredintegrador.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var participants: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        participants = binding.EditTextParticipants
        binding.tvTermsAndCond.setOnClickListener {
            navigate(TermsAndConditions())
        }

        binding.btnStart.setOnClickListener {
            if (participants.text.isNotEmpty() && participants.text.isDigitsOnly() && participants.text.toString().toInt() > 0) {
                navigate(Activities())
            }else {
                participants.error = "Only digits allowed"
            }
        }
    }

    private fun navigate(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        println("Mensaje --> ${participants.text} ")
        intent.putExtra("Participants",participants.text.toString())
        startActivity(intent)
    }
}