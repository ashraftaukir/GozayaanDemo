package com.taukir.gozayaandemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taukir.gozayaandemo.model.Property
import com.taukir.gozayaandemo.repository.PropertyRepository
import kotlinx.coroutines.launch
class PropertyViewModel(private val propertyRepository: PropertyRepository) : ViewModel() {

    private val _properties = MutableLiveData<List<Property>?>() // Allow nullable values
    val properties: LiveData<List<Property>?> get() = _properties

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        fetchProperties()
    }

    private fun fetchProperties() {
        viewModelScope.launch {
            try {
                val propertyList = propertyRepository.getProperties()
                if (propertyList != null) {
                    _properties.value =
                        propertyList // Default to an empty list if propertyList is null
                } else {
                    _errorMessage.value = "Failed to fetch properties."
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}
