package org.deenlabs.localsaggregate.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.deenlabs.localsaggregate.db.LocalsAggregateDatabase
import java.io.File

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val databasePath = "products.db"
        val dbFile = File(databasePath)
        val driver = JdbcSqliteDriver("jdbc:sqlite:$databasePath")
        val schema = LocalsAggregateDatabase.Schema

        if (!dbFile.exists()) {
          schema.create(driver)
          driver.execute(null, "PRAGMA user_version = ${schema.version}", 0)
        }
        else {
          println("Database file already exists at: ${dbFile.absolutePath}")
        }
        return driver
    }
}
