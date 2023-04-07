package com.example.wsr.Api.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wsr.Api.CatalogItem

class SharedViewModel : ViewModel() {

    private val _selectedItem = MutableLiveData<CatalogItem>()
    val selectedItem: LiveData<CatalogItem> get() = _selectedItem

    // Метод для обновления выбранного элемента
    fun updateSelectedItem(item: CatalogItem) {
        _selectedItem.value = item
    }
}
