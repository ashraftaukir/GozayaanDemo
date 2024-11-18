package com.taukir.gozayaandemo.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taukir.gozayaandemo.R
import com.taukir.gozayaandemo.databinding.ItemPropertyBinding
import com.taukir.gozayaandemo.model.Property

class PropertyAdapter(private val properties: List<Property>,
                      private val onPropertyClick: (Property) -> Unit // Lambda for handling item click


) :
    RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    // ViewHolder with ViewBinding
    class PropertyViewHolder(val binding: ItemPropertyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        // Inflate the binding for each item
        val binding = ItemPropertyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PropertyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = properties[position]

        // Bind data using ViewBinding
        holder.binding.apply {
            // Set property name and location
            textViewPropertyName.text = property.property_name
            textViewLocation.text = property.location

            // Load the hero image using Glide
            Glide.with(holder.itemView.context)
                .load(property.hero_image)
                .placeholder(R.drawable.ic_hotel)
                .error(R.mipmap.ic_launcher_round) // Replace with your error image resource
                .into(imageView)

            // Set click listener on mainConstraintLayout
            mainConstraintLayout.setOnClickListener {
                onPropertyClick(property) // Trigger the click callback and pass the property
            }
        }

    }

    override fun getItemCount(): Int = properties.size
}
