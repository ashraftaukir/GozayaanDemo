package com.taukir.gozayaandemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.taukir.gozayaandemo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    // ViewModel with factory for data management
    private val propertyViewModel: PropertyViewModel by viewModels {
        ViewModelFactory(PropertyRepository(RetrofitInstance.api))
    }

    // Declare binding variable for ViewBinding
    private lateinit var binding: ActivityHomeBinding

    // RecyclerView adapter for property items
    private lateinit var propertyAdapter: PropertyAdapter

    // Property list
    private var propertyList: List<Property> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup UI components
        setupRecyclerView()
        observeViewModel()
        setupClickListeners()
    }


    // Sets up the RecyclerView with a horizontal LinearLayoutManager

    private fun setupRecyclerView() {
        binding.recommendedRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }


     // Observes LiveData from ViewModel for property data and error messages

    private fun observeViewModel() {
        // Observe the properties data
        propertyViewModel.properties.observe(this) { properties ->
            if (properties != null) {
                propertyList = properties // Save the property list for later use
                propertyList = properties  // Store the properties list
                propertyAdapter = PropertyAdapter(properties) { selectedProperty ->
                    // Lambda function to handle item click
                    navigateToPropertyDetails(selectedProperty)
                }
                binding.recommendedRecyclerView.adapter = propertyAdapter
            }
        }

        // Observe error messages from ViewModel
        propertyViewModel.errorMessage.observe(this) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                showToast(errorMessage)
            }
        }
    }


     // Sets up click listeners for UI elements

    private fun setupClickListeners() {
        // Handle "See All" button click
        binding.seeAllTextView.setOnClickListener {
            navigateToViewAllProperties()
        }
    }


     // Navigates to the ViewAllPropertyActivity

    private fun navigateToViewAllProperties() {
        val intent = Intent(this, ViewAllPropertyActivity::class.java)

        // Serialize the property list to JSON
        val gson = Gson()
        val json = gson.toJson(propertyList)

        // Pass the JSON string via Intent
        intent.putExtra("property_list", json)
        startActivity(intent)


    }


     // Displays a toast message

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Navigates to PropertyDetailsActivity with the selected property
    private fun navigateToPropertyDetails(property: Property) {
        val intent = Intent(this, PropertyDetailsActivity::class.java)

        // Serialize the property object into a JSON string
        val gson = Gson()
        val propertyJson = gson.toJson(property)
        intent.putExtra("property_details", propertyJson)  // Pass the property as JSON

        // Start the activity
        startActivity(intent)
    }
}
