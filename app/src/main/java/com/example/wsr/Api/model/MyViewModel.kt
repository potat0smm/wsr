package com.example.wsr.Api.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsr.Api.CatalogItem
import com.example.wsr.Api.RetrofitClient
import kotlinx.coroutines.launch


class MyViewModel : ViewModel() {

    private val itemList = MutableLiveData<List<CatalogItem>>()

    // Метод для получения данных из API и обновления списка продуктов
    fun updateItemListFromApi(category: String) {
        // Выполняем HTTP-запрос для получения данных из API
        // В этом примере используется Retrofit, но вы также можете использовать другую библиотеку
        viewModelScope.launch {
            try {
                val apiService = RetrofitClient.apiService
                val response = apiService.getCategory(category)
                if (response.isSuccessful) {
                    // Обновляем список продуктов в ViewModel
                    itemList.value = response.body()
                } else {
                    // Обрабатываем ошибку
                    // ...
                }
            } catch (e: Exception) {
                // Обрабатываем ошибку
                // ...
            }
        }
    }

    // Метод для получения списка продуктов из ViewModel
    fun getItemList(): LiveData<List<CatalogItem>> {
        return itemList
    }
}
