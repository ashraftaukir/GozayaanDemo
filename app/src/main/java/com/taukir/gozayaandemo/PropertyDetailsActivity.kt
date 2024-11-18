package com.taukir.gozayaandemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.taukir.gozayaandemo.databinding.ActivityPropertyDetailsBinding

class PropertyDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPropertyDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityPropertyDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBackButton()

        // Retrieve the property JSON string passed in the intent
        val propertyJson = intent.getStringExtra("property_details")

        if (propertyJson != null) {
            // Deserialize the JSON string into a Property object
            val property = Gson().fromJson(propertyJson, Property::class.java)

            populatePropertyDetails(property)

        }

    }

    //Set up the back button to finish the activity

    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun populatePropertyDetails(property: Property) {
        // Set property data dynamically
        binding.propertyName.text = property.property_name
        binding.propertyLocation.text = property.location


        // Set up the image slider with the adapter
        val adapter = ImageSliderAdapter(property.detail_images)
        binding.imageSlider.adapter = adapter

        // Set up TabLayout (dot indicator) with ViewPager2
        TabLayoutMediator(binding.dotIndicator, binding.imageSlider) { _, _ -> }.attach()

        binding.ratingText.text = property.rating.toString()
        binding.propertyDescription.text = property.description
        binding.priceText.text = "$${property.fare}"
        binding.unitTv.text = " /${property.fare_unit}"

        // Set RatingBar value
        binding.materialRatingBar.rating = property.rating / 10
    }
}
