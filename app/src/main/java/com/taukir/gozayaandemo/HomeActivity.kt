package com.taukir.gozayaandemo

import PropertyAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private lateinit var propertyAdapter: PropertyAdapter
    private val propertyViewModel: PropertyViewModel by viewModels {
        ViewModelFactory(PropertyRepository(RetrofitInstance.api))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val recyclerView: RecyclerView = findViewById(R.id.recommendedRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Observe properties data
        propertyViewModel.properties.observe(this) { properties ->
            propertyAdapter = PropertyAdapter(properties)
            recyclerView.adapter = propertyAdapter
        }

        // Observe error messages
        propertyViewModel.errorMessage.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}
