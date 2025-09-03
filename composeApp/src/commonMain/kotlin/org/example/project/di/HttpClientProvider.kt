package org.example.project.di

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

// Expect: implemented per platform
expect fun provideHttpClient(): HttpClient

// Reusable common configuration to avoid duplication across actuals
internal fun HttpClientConfig<*>.installCommonPlugins() {
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                isLenient = true
                explicitNulls = false
            }
        )
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 30_000
        connectTimeoutMillis = 15_000
        socketTimeoutMillis = 30_000
    }
    defaultRequest {
        headers.append("Accept", "application/json")
        // If you have a base URL, set it via request builders in Repos,
        // or uncomment and set here with url { host = "..."; protocol = URLProtocol.HTTPS }
    }
}
