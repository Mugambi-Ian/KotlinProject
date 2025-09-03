package org.example.project.db
import co.touchlab.kermit.Logger
import org.example.project.db.AppDatabase.UserCache
import kotlin.time.ExperimentalTime

class CacheManager(private val database: Database, private val logger: Logger) {

    private val cacheExpiry = 12 * 60 * 60 * 1000 // 12 hours in milliseconds

    @OptIn(ExperimentalTime::class)
    fun getUsers(): List<UserCache> {
        val users = database.getAllUsers()
        logger.i("Users from cache:  $users")
        if (users.isNotEmpty()) {
            val firstUser = users.first()
            val currentTime = kotlin.time.Clock.System.now().toEpochMilliseconds()
            if (currentTime - firstUser.createdAt < cacheExpiry) {
                return users
            }
        }
        return emptyList()
    }

    @OptIn(ExperimentalTime::class)
    fun saveUsers(users: List<UserCache>) {
        database.clearDatabase()
        val currentTime = kotlin.time.Clock.System.now().toEpochMilliseconds()
        users.forEach { user ->
            database.insertUser(user.copy(createdAt = currentTime))
        }
    }
}