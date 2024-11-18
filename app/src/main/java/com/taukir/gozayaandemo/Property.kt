package com.taukir.gozayaandemo

data class Property(
    val property_name: String,
    val location: String,
    val rating: Float,
    val description: String,
    val fare: Double,
    val fare_unit: String,
    val is_available: Boolean,
    val hero_image: String,
    val detail_images: List<String>,
    val currency: String
)

