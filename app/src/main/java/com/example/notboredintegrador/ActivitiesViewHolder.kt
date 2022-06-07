package com.example.notboredintegrador

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notboredintegrador.databinding.ItemTypeBinding

class ActivitiesViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val binding = ItemTypeBinding.bind(view)

    fun bind(type:String){
        binding.tvType.text = type
    }
}
