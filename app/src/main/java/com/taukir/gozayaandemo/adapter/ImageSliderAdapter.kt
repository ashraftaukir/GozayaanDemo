package com.taukir.gozayaandemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taukir.gozayaandemo.databinding.ItemImageBinding

class ImageSliderAdapter(private val images: List<String>) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        // Use ViewBinding to inflate the layout
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = images[position]
        // Use Glide to load the image into the ImageView
        Glide.with(holder.binding.roundedImageView.context)
            .load(imageUrl)
            .into(holder.binding.roundedImageView)
    }

    override fun getItemCount(): Int = images.size

    // ViewHolder using ViewBinding
    class ImageViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)
}
