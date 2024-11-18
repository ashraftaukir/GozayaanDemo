package com.taukir.gozayaandemo.network

import com.taukir.gozayaandemo.model.Property
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    suspend fun getProperties(): Response<List<Property>>
}
