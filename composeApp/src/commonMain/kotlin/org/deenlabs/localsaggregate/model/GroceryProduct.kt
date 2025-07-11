package org.deenlabs.localsaggregate.model

data class GroceryProduct(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val weight: String? = null
)