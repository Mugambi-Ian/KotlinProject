package org.example.project.dashboard.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.project.dashboard.data.model.User

class DashboardRepository(private val httpClient: HttpClient) {
    suspend fun getUsers(): List<User> {
        return httpClient.get("https://microsoftedge.github.io/Demos/json-dummy-data/64KB.json").body()
    }
}
