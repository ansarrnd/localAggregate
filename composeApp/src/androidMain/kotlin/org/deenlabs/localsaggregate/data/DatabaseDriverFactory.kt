package org.deenlabs.localsaggregate.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.deenlabs.localsaggregate.db.LocalsAggregateDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        val driver = AndroidSqliteDriver(LocalsAggregateDatabase.Schema, context, "products.db")

        // Check if the database already exists
        val databaseFile = context.getDatabasePath("products.db")
        if (databaseFile.exists()) {
            println("Database file already exists at: ${databaseFile.absolutePath}")
        }

        return driver
    }
}
