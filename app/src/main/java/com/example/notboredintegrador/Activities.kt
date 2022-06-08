package com.example.notboredintegrador

import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notboredintegrador.databinding.ActivityActivitiesBinding
import com.example.notboredintegrador.recycler.ActivitiesAdapter

class Activities : AppCompatActivity() {
    val activityType = listOf<String>(
        "education", "recreational", "social", "diy", "charity", "cooking", "relaxation", "music", "busywork")
    private lateinit var binding: ActivityActivitiesBinding
    private lateinit var activitiesAdapter: ActivitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val participants = intent?.getStringExtra("Participants").toString()

        println("Mensaje --> Participants ${participants}")

        activitiesAdapter = ActivitiesAdapter(activityType, participants)
        //binding.rvTypeList.addItemDecoration(DividerItemDecoration(this, HORIZONTAL))
        binding.rvTypeList.layoutManager = LinearLayoutManager(this)
        binding.rvTypeList.adapter = activitiesAdapter

        binding.ivShuffleButton.setOnClickListener {
            val intent = Intent(it.context,DetailsActivity::class.java)
            intent.putExtra("Participants", participants)
            it.context.startActivity(intent)
        }
    }

}