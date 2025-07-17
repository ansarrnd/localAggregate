package org.deenlabs.localsaggregate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

sealed class Screen {
    data object RoleSelection : Screen()
    data class Login(val role: String) : Screen()
    data class ProductList(val role: String) : Screen()
    data class StoreOptions(val role: String) : Screen() // New screen
    data class Cart(val role: String) : Screen()
    data class Checkout(val role: String) : Screen()
    data class AddProduct(val role: String) : Screen()
    data class AddStore(val role: String) : Screen()
    data class ListStores(val role: String) : Screen() // New Screen
}

class NavigationViewModel : ViewModel() {
    var currentScreen: Screen by mutableStateOf(Screen.RoleSelection)
        private set

    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }

    fun onBack() {
        val current = currentScreen
        currentScreen = when (current) {
            is Screen.Login -> Screen.RoleSelection
            is Screen.ProductList -> if (current.role == "Store") Screen.StoreOptions(current.role) else Screen.Login(
                current.role
            )

            is Screen.StoreOptions -> Screen.Login(current.role)
            is Screen.Cart -> Screen.ProductList(current.role)
            is Screen.Checkout -> Screen.Cart(current.role)
            is Screen.AddProduct -> Screen.ProductList(current.role)
            is Screen.AddStore -> Screen.StoreOptions(current.role)
            is Screen.ListStores -> Screen.StoreOptions(current.role)
            is Screen.RoleSelection -> Screen.RoleSelection // Or handle app exit
        }

    }
}