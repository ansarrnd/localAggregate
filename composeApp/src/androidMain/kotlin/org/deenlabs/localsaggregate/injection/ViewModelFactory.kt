package org.deenlabs.localsaggregate.injection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import org.deenlabs.localsaggregate.data.DatabaseDriverFactory
import org.deenlabs.localsaggregate.data.SqlDelightProductDataSource
import org.deenlabs.localsaggregate.db.LocalsAggregateDatabase
import org.deenlabs.localsaggregate.viewmodel.ProductViewModel

@Composable
actual fun getProductViewModel(): ProductViewModel {
    val context = LocalContext.current.applicationContext
    return remember {
        val database = LocalsAggregateDatabase(DatabaseDriverFactory(context).createDriver())
        ProductViewModel(SqlDelightProductDataSource(database))
    }
}