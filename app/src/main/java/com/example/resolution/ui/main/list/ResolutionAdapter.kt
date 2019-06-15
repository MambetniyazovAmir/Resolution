package com.example.resolution.ui.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resolution.R
import com.example.resolution.callback.OnImageItemClickListener
import com.example.resolution.data.ImageModel

class ResolutionAdapter(private val onItemClickListener: OnImageItemClickListener) : RecyclerView.Adapter<ResolutionViewHolder>() {

    private var models: List<ImageModel> = arrayListOf()

    fun setData(models: List<ImageModel>) {
        this.models = models
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResolutionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ResolutionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: ResolutionViewHolder, position: Int) {
        holder.populateModel(models[position], onItemClickListener)
    }
}