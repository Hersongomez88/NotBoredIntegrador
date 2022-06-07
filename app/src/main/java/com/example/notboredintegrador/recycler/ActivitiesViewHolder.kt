package com.example.notboredintegrador.recycler

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notboredintegrador.DetailsActivity
import com.example.notboredintegrador.MainActivity
import com.example.notboredintegrador.databinding.ItemTypeBinding

class ActivitiesViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val binding = ItemTypeBinding.bind(view)

    fun bind(type:String, participants: String){
        binding.tvType.text = type
        binding.itemTypeContainer.setOnClickListener {
            val intent = Intent(it.context,DetailsActivity::class.java)
            println("Mensaje --> $type")
            intent.putExtra("ActivityType", type)
            intent.putExtra("Participants", participants)
            it.context.startActivity(intent)
        }
    }
}
