package com.example.notboredintegrador.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notboredintegrador.R

class ActivitiesAdapter(val activityType: List<String>, val participants : String ): RecyclerView.Adapter<ActivitiesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesViewHolder {
        val view =
            LayoutInflater.from(parent.context)
        return ActivitiesViewHolder(view.inflate(R.layout.item_type, parent, false))
    }

    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        val typePosition = activityType[position]
        holder.bind(typePosition, participants)
    }

    override fun getItemCount(): Int = activityType.size

}


