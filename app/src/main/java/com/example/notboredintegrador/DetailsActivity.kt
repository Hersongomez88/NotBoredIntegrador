package com.example.notboredintegrador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val activityType = intent.getStringExtra("ActivityType")
        val participants = intent.getStringExtra("Participants")

        println("Mensaje --> $activityType recibido y el numero de participantes es: $participants")
        val texto = findViewById<TextView>(R.id.TvActicityType)
        texto.setText("La actividad elegida es: ${activityType} \ny el numero de participantes para esta actividad es: $participants")
    }
}