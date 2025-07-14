package org.deenlabs.localsaggregate.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.deenlabs.localsaggregate.model.GroceryProduct

class ProductViewModel : ViewModel() {
    private val _products = mutableStateListOf<GroceryProduct>()
    val products: List<GroceryProduct> = _products

    fun addProduct(name: String, price: Double, weight: String?) {
        val newId = (_products.maxOfOrNull { it.id.toIntOrNull() ?: 0 } ?: 0) + 1
        _products.add(GroceryProduct(id = newId.toString(), name = name, price = price, imageUrl = "", weight = weight))
    }
}