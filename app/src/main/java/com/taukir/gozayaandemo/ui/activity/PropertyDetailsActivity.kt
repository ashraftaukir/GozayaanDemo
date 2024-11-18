package com.taukir.gozayaandemo.ui.activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.taukir.gozayaandemo.adapter.ImageSliderAdapter
import com.taukir.gozayaandemo.model.Property
import com.taukir.gozayaandemo.R
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

        // Set up custom drawable indicator
        setupDrawableIndicator(property.detail_images.size)

        binding.ratingText.text = property.rating.toString()
        binding.propertyDescription.text = property.description
        binding.priceText.text = "$${property.fare}"
        binding.unitTv.text = " /${property.fare_unit}"

        // Set RatingBar value
        binding.materialRatingBar.rating = property.rating / 10
    }

    // Set up the drawable indicator
    private fun setupDrawableIndicator(pageCount: Int) {
        val indicatorContainer = binding.drawableIndicator
        indicatorContainer.removeAllViews() // Clear any previous indicators

        // Add the correct number of indicators
        for (i in 0 until pageCount) {
            val dot = ImageView(this)
            // Set the initial drawable for each dot (unselected)
            dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.normal_icon))

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            // Set margins to add space between the dots
            val margin = resources.getDimensionPixelSize(R.dimen.dot_margin) // Use a defined margin
            params.setMargins(margin, 0, margin, 0) // Left and right margins

            dot.layoutParams = params
            // Add the dot to the container
            indicatorContainer.addView(dot)
        }

        // Set up the page change listener to update the indicators
        binding.imageSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicator(position, pageCount)
            }
        })
    }


    // Update the indicator based on the selected page
    private fun updateIndicator(position: Int, pageCount: Int) {
        val indicatorContainer = binding.drawableIndicator
        for (i in 0 until pageCount) {
            val dot = indicatorContainer.getChildAt(i) as ImageView
            if (i == position) {
                // Set the selected dot drawable
                dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.checked_icon))
            } else {
                // Set the unselected dot drawable
                dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.normal_icon))
            }
        }
    }
}
