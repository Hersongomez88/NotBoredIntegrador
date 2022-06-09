package com.example.notboredintegrador.recycler

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notboredintegrador.DetailsActivity
import com.example.notboredintegrador.MainActivity
import com.example.notboredintegrador.databinding.ItemTypeBinding

class ActivitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemTypeBinding.bind(view)

    /**
     * Sets the name of the category and the click event.
     * When the item is selected it navigates to the details activity*/
    fun bind(type: String, participants: String) {
        binding.tvType.text = type

        /* When the user selects an activity type, navigate to the next screen and send the type
         * along with the number of participants */
        binding.itemTypeContainer.setOnClickListener {
            val intent = Intent(it.context, DetailsActivity::class.java)

            intent.putExtra("ActivityType", type)
            intent.putExtra("Participants", participants)
            it.context.startActivity(intent)
        }
    }
}
