package org.deenlabs.localsaggregate.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.deenlabs.localsaggregate.model.CartItem
import org.deenlabs.localsaggregate.model.GroceryProduct

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> = _cartItems

    val totalPrice: Double
        get() = _cartItems.sumOf { it.product.price * it.quantity }

    val totalItemCount: Int
        get() = _cartItems.sumOf { it.quantity }

    fun addToCart(product: GroceryProduct) {
        val existingItem = _cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            increaseQuantity(existingItem)
        } else {
            _cartItems.add(CartItem(product = product, quantity = 1))
        }
    }

    fun increaseQuantity(item: CartItem) {
        val index = _cartItems.indexOf(item)
        if (index != -1) {
            _cartItems[index] = item.copy(quantity = item.quantity + 1)
        }
    }

    fun decreaseQuantity(item: CartItem) {
        val index = _cartItems.indexOf(item)
        if (index != -1) {
            if (item.quantity > 1) {
                _cartItems[index] = item.copy(quantity = item.quantity - 1)
            } else {
                removeItem(item)
            }
        }
    }

    fun removeItem(item: CartItem) {
        _cartItems.remove(item)
    }

    fun clearCart() {
        _cartItems.clear()
    }

}