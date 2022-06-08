package com.example.notboredintegrador

import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notboredintegrador.databinding.ActivityActivitiesBinding
import com.example.notboredintegrador.recycler.ActivitiesAdapter

class Activities : AppCompatActivity() {
    val activityType = listOf<String>(
        "Education", "Recreational", "Social", "Diy", "Charity", "Cooking", "Relaxation", "Music", "Busywork")
    private lateinit var binding: ActivityActivitiesBinding
    private lateinit var activitiesAdapter: ActivitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val participants = intent?.getStringExtra("Participants").toString()


        activitiesAdapter = ActivitiesAdapter(activityType, participants)
        binding.rvTypeList.layoutManager = LinearLayoutManager(this)
        binding.rvTypeList.adapter = activitiesAdapter

        binding.ivShuffleButton.setOnClickListener {
            val intent = Intent(it.context,DetailsActivity::class.java)
            intent.putExtra("Participants", participants)
            it.context.startActivity(intent)
        }
    }

}