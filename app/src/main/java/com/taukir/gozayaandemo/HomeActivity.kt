package com.taukir.gozayaandemo

import PropertyAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.taukir.gozayaandemo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var propertyAdapter: PropertyAdapter
    private val propertyViewModel: PropertyViewModel by viewModels {
        ViewModelFactory(PropertyRepository(RetrofitInstance.api))
    }

    // Declare binding variable
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView using ViewBinding
        binding.recommendedRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Observe properties data
        propertyViewModel.properties.observe(this) { properties ->
            propertyAdapter = PropertyAdapter(properties)
            binding.recommendedRecyclerView.adapter = propertyAdapter
        }

        // Observe error messages
        propertyViewModel.errorMessage.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}
