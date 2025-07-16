package org.deenlabs.localsaggregate.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.deenlabs.localsaggregate.db.LocalsAggregateDatabase
import java.io.File

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val databasePath = "products.db"
        val driver = JdbcSqliteDriver("jdbc:sqlite:$databasePath")
        if (!File(databasePath).exists()) {
            LocalsAggregateDatabase.Schema.create(driver)
        }
        return driver
    }
}