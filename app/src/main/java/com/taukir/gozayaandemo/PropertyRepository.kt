package com.taukir.gozayaandemo

import ApiService
import retrofit2.Response

class PropertyRepository(private val apiService: ApiService) {

    // Fetch properties from the API
    suspend fun getProperties(): Response<List<Property>> {
        return try {
            apiService.getProperties()
        } catch (e: Exception) {
            // Log and handle exceptions
            Response.error(500, okhttp3.ResponseBody.create(null, "Error fetching data"))
        }
    }
}
