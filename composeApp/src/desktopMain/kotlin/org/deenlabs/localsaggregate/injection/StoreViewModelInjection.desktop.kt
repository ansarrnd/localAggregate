package org.deenlabs.localsaggregate.injection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.deenlabs.localsaggregate.data.DatabaseDriverFactory
import org.deenlabs.localsaggregate.db.LocalsAggregateDatabase
import org.deenlabs.localsaggregate.viewmodel.StoreViewModel

@Composable
actual fun getStoreViewModel(): StoreViewModel {
    val driver = remember { DatabaseDriverFactory().createDriver() }
    val database = remember { LocalsAggregateDatabase(driver) }
    val storeQueries = remember { database.localsAggregateQueries }

    return remember { StoreViewModel(storeQueries) } // Adjust constructor arguments as needed
}