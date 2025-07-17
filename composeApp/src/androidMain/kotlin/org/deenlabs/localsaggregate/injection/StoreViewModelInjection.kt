package org.deenlabs.localsaggregate.injection

import org.deenlabs.localsaggregate.data.SqlDelightStoreDataSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import org.deenlabs.localsaggregate.data.DatabaseDriverFactory
import org.deenlabs.localsaggregate.db.LocalsAggregateDatabase
import org.deenlabs.localsaggregate.viewmodel.StoreViewModel

@Composable
actual fun getStoreViewModel(): StoreViewModel {
    val context = LocalContext.current.applicationContext
    return remember {
        val database = LocalsAggregateDatabase(DatabaseDriverFactory(context).createDriver())
        val storeQueries = database.localsAggregateQueries
        StoreViewModel(storeQueries)
    }
}