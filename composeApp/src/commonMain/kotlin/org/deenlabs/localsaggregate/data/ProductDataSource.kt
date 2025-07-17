package org.deenlabs.localsaggregate.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.deenlabs.localsaggregate.db.LocalsAggregateDatabase
import org.deenlabs.localsaggregate.model.GroceryProduct
import org.deenlabs.localsaggregate.db.Product as ProductEntity

interface ProductDataSource {
    fun getAllProducts(): Flow<List<GroceryProduct>>
    suspend fun insertProduct(name: String, price: Double, weight: String?, imageUrl: String)
}

class SqlDelightProductDataSource(db: LocalsAggregateDatabase) : ProductDataSource {

    private val queries = db.localsAggregateQueries // Access the generated queries here

    override fun getAllProducts(): Flow<List<GroceryProduct>> {
        return queries.getAllProducts()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { productEntities ->
                productEntities.map { it.toGroceryProduct() }
            }
    }

    override suspend fun insertProduct(name: String, price: Double, weight: String?, imageUrl: String) {
        withContext(Dispatchers.Default) {
            queries.insertProduct(name = name, price = price, weight = weight, imageUrl = imageUrl)
        }
    }

    private fun ProductEntity.toGroceryProduct(): GroceryProduct {
        return GroceryProduct(id = this.id.toString(), name = this.name, price = this.price, weight = this.weight, imageUrl = this.imageUrl)
    }
}