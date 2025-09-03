package org.example.project.modules.dashboard.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.project.db.CacheManager
import org.example.project.db.AppDatabase.UserCache
import org.example.project.modules.dashboard.data.model.User
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class DashboardRepository(
    private val httpClient: HttpClient,
    private val cacheManager: CacheManager
) {
    suspend fun getUsers(): List<User> {
        val cachedUsers = cacheManager.getUsers()
        if (cachedUsers.isNotEmpty()) {
            return cachedUsers.map { it.toUser() }
        }

        val newUsers =
            httpClient.get("https://microsoftedge.github.io/Demos/json-dummy-data/64KB.json")
                .body<List<User>>()
        cacheManager.saveUsers(newUsers.map { it.toUserCache() })
        return newUsers
    }
}

@OptIn(ExperimentalTime::class)
private fun User.toUserCache(): UserCache {
    return UserCache(
        id = id,
        name = name,
        bio = bio,
        language = language,
        version = version.toLong(),
        profilePicture = profilePicture,
        createdAt = Clock.System.now().toEpochMilliseconds()
    )
}

private fun UserCache.toUser(): User {
    return User(
        id = id,
        name = name,
        bio = bio ?: "",
        language = language ?: "",
        version = version?.toDouble() ?: 0.0,
        profilePicture = profilePicture,
    )
}