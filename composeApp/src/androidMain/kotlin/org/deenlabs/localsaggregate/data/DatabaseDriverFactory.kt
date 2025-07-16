package org.deenlabs.localsaggregate.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.deenlabs.localsaggregate.db.LocalsAggregateDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(LocalsAggregateDatabase.Schema, context, "products.db")
}