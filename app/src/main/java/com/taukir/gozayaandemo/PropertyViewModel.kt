package com.taukir.gozayaandemo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PropertyViewModel(private val propertyRepository: PropertyRepository) : ViewModel() {

    private val _properties = MutableLiveData<List<Property>>()
    val properties: LiveData<List<Property>> get() = _properties

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        fetchProperties()
    }

    // Fetch properties from repository
    private fun fetchProperties() {
        viewModelScope.launch {
            try {
                val response = propertyRepository.getProperties()
                if (response.isSuccessful) {
                    _properties.value = response.body()
                } else {
                    _errorMessage.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                Log.e("PropertyViewModel", "Error fetching properties: ${e.message}")
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}
