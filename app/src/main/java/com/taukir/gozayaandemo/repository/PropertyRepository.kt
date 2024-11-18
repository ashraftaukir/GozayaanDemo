package com.taukir.gozayaandemo.repository

import com.taukir.gozayaandemo.network.ApiService
import com.taukir.gozayaandemo.model.Property

class PropertyRepository(private val apiService: ApiService) {

    // Fetch properties using coroutine and handle exceptions cleanly
    suspend fun getProperties(): List<Property>? {
        return try {
            val response = apiService.getProperties()
            if (response.isSuccessful) {
                response.body() // Return the list of properties if successful
            } else {
                throw Exception("Error: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            e.printStackTrace() // Log the exception
            null // Return null in case of error
        }
    }
}

