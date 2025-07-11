package org.deenlabs.localsaggregate.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.deenlabs.localsaggregate.model.GroceryProduct

class ProductViewModel : ViewModel() {
    private val _products = mutableStateListOf(
        GroceryProduct("1", "Fresh Apples", 2.99, "", "1kg"),
        GroceryProduct("2", "Organic Bananas", 1.50, "", "per bunch"),
        GroceryProduct("3", "Whole Wheat Bread", 3.49, "", "1 loaf"),
        GroceryProduct("4", "Free-Range Eggs", 4.99, "", "1 dozen"),
        GroceryProduct("5", "Milk (1 Gallon)", 3.79, "", "1 gal"),
        GroceryProduct("6", "Cheddar Cheese", 5.25, "", "8oz block"),
        GroceryProduct("7", "Chicken Breast", 9.99, "", "per lb"),
        GroceryProduct("8", "Tomatoes", 2.20, "", "per lb"),
        GroceryProduct("9", "Spinach", 2.00, "", "1 bag"),
        GroceryProduct("10", "Pasta", 1.29, "", "500g")
    )
    val products: List<GroceryProduct> = _products

    fun addProduct(name: String, price: Double, weight: String?) {
        val newId = (_products.maxOfOrNull { it.id.toInt() } ?: 0) + 1
        _products.add(GroceryProduct(id = newId.toString(), name = name, price = price, imageUrl = "", weight = weight))
    }
}