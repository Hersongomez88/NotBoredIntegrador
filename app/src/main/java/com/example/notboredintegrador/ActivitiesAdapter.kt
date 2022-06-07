package com.example.notboredintegrador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ActivitiesAdapter(var activityType: List<String>): RecyclerView.Adapter<ActivitiesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesViewHolder {
        val view =
            LayoutInflater.from(parent.context)
        return ActivitiesViewHolder(view.inflate(R.layout.item_type, parent, false))
    }

    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        val typePosition = activityType[position]
        holder.bind(typePosition)
    }

    override fun getItemCount(): Int = activityType.size

}


