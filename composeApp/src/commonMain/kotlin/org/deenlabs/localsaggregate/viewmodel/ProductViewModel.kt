package org.deenlabs.localsaggregate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.deenlabs.localsaggregate.data.ProductDataSource
import org.deenlabs.localsaggregate.model.GroceryProduct

class ProductViewModel(
    private val productDataSource: ProductDataSource
) : ViewModel() {

    val products = productDataSource.getAllProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addProduct(name: String, price: Double, weight: String?) {
        viewModelScope.launch {
            productDataSource.insertProduct(name = name, price = price, weight = weight, imageUrl = "" // Default empty image url
            )
        }
    }
}