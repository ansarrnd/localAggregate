package org.deenlabs.localsaggregate.viewmodel

import androidx.lifecycle.ViewModel
import app.cash.sqldelight.coroutines.asFlow
import androidx.lifecycle.viewModelScope
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.deenlabs.localsaggregate.db.LocalsAggregateQueries
import org.deenlabs.localsaggregate.model.Store

class StoreViewModel(private val queries: LocalsAggregateQueries) : ViewModel() {

    val stores: StateFlow<List<Store>> = queries.getAllStores()
        .asFlow()
        .mapToList(Dispatchers.IO)
        .map { storeEntities ->
            storeEntities.map { Store(it.id.toString(), it.name, it.location) }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addStore(name: String, location: String) {
        viewModelScope.launch { queries.insertStore(name, location) }
    }
}