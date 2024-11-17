package com.taukir.gozayaandemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
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

        // Example dynamic image URLs
        val imageUrls = listOf(
            "https://images.pexels.com/photos/261102/pexels-photo-261102.jpeg",
            "https://images.pexels.com/photos/271639/pexels-photo-271639.jpeg",
            "https://images.pexels.com/photos/258154/pexels-photo-258154.jpeg"
        )

        // Set up the ViewPager2 with the adapter
        val adapter = ImageSliderAdapter(imageUrls)
        binding.imageSlider.adapter = adapter

        // Set up TabLayout (dot indicator) with ViewPager2
        TabLayoutMediator(binding.dotIndicator, binding.imageSlider) { _, _ -> }.attach()



        val propertyName = "Mountain Safari"
        val propertyLocation = "Kolkata, India"
        var propertyRating = 4.9f // Dynamic rating value
        binding.ratingText.text = propertyRating.toString()
        propertyRating /= 10
        val propertyDescription =
            "List of Inspiring Slogans a fresh coat for a fresh start meet the world make traveling fun explore the globe with a new sky, a new life..."
        val propertyPrice = "$5,307"

        // Set property data
        binding.propertyName.text = propertyName
        binding.propertyLocation.text = propertyLocation
//        binding.ratingText.text = propertyRating.toString()
        binding.propertyDescription.text = propertyDescription
        binding.priceText.text = propertyPrice

        // Set RatingBar value
        binding.materialRatingBar.rating = propertyRating
    }
}
