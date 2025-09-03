package org.example.project.db

import app.cash.sqldelight.db.SqlDriver
import org.example.project.db.AppDatabase.AppDatabase
import org.example.project.db.AppDatabase.UserCache


expect class DriverFactory {
    fun createDriver(): SqlDriver
}

class Database(driverFactory: DriverFactory) {
    private val driver = driverFactory.createDriver()
    private val database = AppDatabase(driver)

    fun clearDatabase() {
        database.userCacheQueries.deleteAll()
    }

    fun getAllUsers(): List<UserCache> {
        return database.userCacheQueries.selectAll().executeAsList()
    }

    fun insertUser(user: UserCache) {
        database.userCacheQueries.insert(
            id = user.id,
            name = user.name,
            language = user.language,
            bio = user.bio,
            version = user.version,
            profilePicture = user.profilePicture,
            createdAt = user.createdAt
        )
    }
}