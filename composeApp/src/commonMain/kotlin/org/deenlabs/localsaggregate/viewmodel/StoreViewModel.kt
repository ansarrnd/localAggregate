package org.deenlabs.localsaggregate.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.deenlabs.localsaggregate.model.Store
import java.util.concurrent.atomic.AtomicLong

class StoreViewModel : ViewModel() {
    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores = _stores.asStateFlow()

    private val nextId = AtomicLong(0)

    fun addStore(name: String, location: String) {
        val newStore = Store(id = nextId.incrementAndGet(), name = name, location = location)
        _stores.value = _stores.value + newStore
    }
}