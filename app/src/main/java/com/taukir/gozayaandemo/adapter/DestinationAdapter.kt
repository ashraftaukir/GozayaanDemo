package com.taukir.gozayaandemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taukir.gozayaandemo.R
import com.taukir.gozayaandemo.databinding.ItemDestinationBinding
import com.taukir.gozayaandemo.model.Property


class DestinationAdapter(private val destinations: List<Property>) :
    RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemDestinationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(destination: Property) {
            Glide.with(itemView.context)
                .load(destination.hero_image)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.error_image_with_black_background)
                .into(binding.destinationImage)

            binding.destinationTitle.text = destination.property_name
            binding.destinationLocation.text = destination.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDestinationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(destinations[position])
    }

    override fun getItemCount(): Int = destinations.size
}
