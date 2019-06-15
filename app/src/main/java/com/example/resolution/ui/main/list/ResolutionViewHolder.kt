package com.example.resolution.ui.main.list

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.resolution.R
import com.example.resolution.callback.OnImageItemClickListener
import com.example.resolution.data.ImageModel
import com.squareup.picasso.Picasso

class ResolutionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val originalImage: ImageView = itemView.findViewById(R.id.originalImage)
    private val superImage: ImageView = itemView.findViewById(R.id.superImage)

    fun populateModel(model: ImageModel, onItemClickListener: OnImageItemClickListener) {

        Log.d("patpelek", model.original_image)

        Picasso.get()
            .load(model.original_image)
            .into(originalImage)

        Picasso.get()
            .load(model.super_image)
            .into(superImage)

        itemView.setOnClickListener {
            onItemClickListener.onItemClick(model)
        }
    }
}