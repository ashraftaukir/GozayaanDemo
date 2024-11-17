package com.taukir.gozayaandemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.taukir.gozayaandemo.databinding.ActivityViewAllBinding

class ViewAllPropertyActivity : AppCompatActivity() {

    // ViewBinding instance
    private lateinit var binding: ActivityViewAllBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBackButton()
        loadPropertyData()
    }



     //Set up the back button to finish the activity

    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }


     // Load property data from the Intent and set up the RecyclerView

    private fun loadPropertyData() {
        // Retrieve JSON string from Intent
        val json = intent.getStringExtra("property_list")
        if (json.isNullOrEmpty()) {
            showError("No property data available.")
            return
        }

        // Deserialize JSON into a list of Property objects
        val propertyList = deserializePropertyData(json)

        // Set up RecyclerView with the data
        setupRecyclerView(propertyList)
    }


     // Deserialize the JSON string into a list of Property objects

    private fun deserializePropertyData(json: String): List<Property> {
        val gson = Gson()
        val type = object : TypeToken<List<Property>>() {}.type
        return gson.fromJson(json, type)
    }


     // Set up the RecyclerView with a grid layout and data

    private fun setupRecyclerView(properties: List<Property>) {
        val adapter = DestinationAdapter(properties)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter
    }


     // Show an error message

    private fun showError(message: String) {
        // You can implement a Toast or Snackbar here for better UX
        println("Error: $message")
    }
}
