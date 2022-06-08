package com.example.notboredintegrador

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

        participants.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                toggleButtonStart()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                toggleButtonStart()
            }
        })
        binding.tvTermsAndCond.setOnClickListener {
            navigate(TermsAndConditions())
        }

        binding.btnStart.setOnClickListener {
            if (isUserInputValid()) {
                navigate(Activities())
            }else {
                participants.error = "Only digits allowed"
            }
        }
    }

    private fun isUserInputValid(): Boolean {
        val textInput = participants.text
        return textInput.isNotEmpty() && textInput.isDigitsOnly() && textInput.toString().toInt() > 0
    }

    private fun toggleButtonStart() {
        if (isUserInputValid()){
            binding.btnStart.isEnabled = true
        }else{
            binding.btnStart.isEnabled = false
            participants.error = "Enter a number of participants"
        }
    }

    private fun navigate(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        intent.putExtra("Participants",participants.text.toString())
        startActivity(intent)
    }
}