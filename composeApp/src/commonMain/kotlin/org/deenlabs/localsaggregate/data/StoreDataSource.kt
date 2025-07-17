package org.deenlabs.localsaggregate.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.deenlabs.localsaggregate.db.Store as StoreEntity
import org.deenlabs.localsaggregate.model.Store
import org.deenlabs.localsaggregate.db.LocalsAggregateDatabase
import kotlinx.coroutines.withContext

interface StoreDataSource {
    fun getAllStores(): Flow<List<Store>>
    suspend fun insertStore(name: String, location: String)
}

class SqlDelightStoreDataSource(db: LocalsAggregateDatabase) : StoreDataSource {

    private val queries = db.localsAggregateQueries

    override fun getAllStores(): Flow<List<Store>> {
        return queries.getAllStores().asFlow().mapToList(Dispatchers.Default).map{storeEntities ->
            storeEntities.map { it.toStore() }
        }
    }
    override suspend fun insertStore(name: String, location: String) {
        withContext(Dispatchers.Default) {
            queries.insertStore(name = name, location = location)
        }
    }

    private fun StoreEntity.toStore(): Store = Store(id = this.id.toString(), name = this.name, location = this.location)
}