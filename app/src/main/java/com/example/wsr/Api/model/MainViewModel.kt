package com.example.wsr.Api.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsr.Api.BaseUrl
import com.example.wsr.Api.Catalog
import com.example.wsr.Api.CatalogItem
import com.example.wsr.Api.SimpleApi
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import java.lang.reflect.Array.get


class MainViewModel(private val simpleApi: SimpleApi) : ViewModel() {

    private val _catalogList = MutableLiveData<List<Catalog>>()
    val catalogList: LiveData<List<Catalog>> = _catalogList

    private val _cartItems = MutableLiveData<MutableList<CatalogItem>>()
    val cartItems: LiveData<MutableList<CatalogItem>> = _cartItems

    private val _totalPrice = MutableLiveData<Int>()
    val totalPrice: LiveData<Int> = _totalPrice

    init {
        _cartItems.value = mutableListOf()
        _totalPrice.value = 0
        loadCatalog()
    }

    private fun loadCatalog() {
        viewModelScope.launch {
            try {
                val response = simpleApi.getCatalog()
                if (response.isSuccessful) {
                    _catalogList.value = response.body()
                }
            } catch (e: Exception) {
                // handle error
            }
        }
    }

    fun addItemToCart(item: CatalogItem) {
        val cartItems = _cartItems.value ?: mutableListOf()
        cartItems.add(item)
        _cartItems.value = cartItems
        updateTotalPrice()
    }

    fun removeItemFromCart(item: CatalogItem) {
        val cartItems = _cartItems.value ?: mutableListOf()
        cartItems.remove(item)
        _cartItems.value = cartItems
        updateTotalPrice()
    }

    private fun updateTotalPrice() {
        var total = 0
        _cartItems.value?.forEach {
            total += it.price.toInt()
        }
        _totalPrice.value = total
    }

    companion object {
        val modul = module {
            viewModel { MainViewModel(get()) }
        }
    }
}

