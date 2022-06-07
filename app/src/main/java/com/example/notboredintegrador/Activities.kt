package com.example.notboredintegrador

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notboredintegrador.databinding.ActivityActivitiesBinding
import com.example.notboredintegrador.databinding.ActivityMainBinding

class Activities : AppCompatActivity() {
    val activityType = listOf<String>(
        "education", "recreational", "social", "diy", "charity", "cooking", "relaxation", "music", "busywork")
    private lateinit var binding: ActivityActivitiesBinding
    private lateinit var activitiesAdapter: ActivitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        activitiesAdapter = ActivitiesAdapter(activityType)
        binding.rvTypeList.addItemDecoration(DividerItemDecoration(this, HORIZONTAL))
        binding.rvTypeList.layoutManager = LinearLayoutManager(this)
        binding.rvTypeList.adapter = activitiesAdapter
    }

}