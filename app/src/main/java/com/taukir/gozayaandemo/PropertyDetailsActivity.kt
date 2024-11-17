package com.taukir.gozayaandemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taukir.gozayaandemo.databinding.ActivityPropertyDetailsBinding

class PropertyDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPropertyDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityPropertyDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Populate rating and data dynamically
        populatePropertyDetails()
    }

    private fun populatePropertyDetails() {
        val propertyName = "Mountain Safari"
        val propertyLocation = "Kolkata, India"
        val propertyRating = 3.5f // Dynamic rating value
        val propertyDescription =
            "List of Inspiring Slogans a fresh coat for a fresh start meet the world make traveling fun explore the globe with a new sky, a new life..."
        val propertyPrice = "$5,307 / PER DAY"

        // Set property data
        binding.propertyName.text = propertyName
        binding.propertyLocation.text = propertyLocation
        binding.ratingText.text = propertyRating.toString()
        binding.propertyDescription.text = propertyDescription
        binding.priceText.text = propertyPrice

        // Set RatingBar value
        binding.ratingBar.rating = propertyRating
    }
}
