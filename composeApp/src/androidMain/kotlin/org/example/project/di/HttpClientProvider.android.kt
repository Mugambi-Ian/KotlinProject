package org.example.project.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

actual fun provideHttpClient(): HttpClient = HttpClient(OkHttp) {
    installCommonPlugins()

    engine {
        // Optional fine-tuning of OkHttp
        config {
            followRedirects(true)
            retryOnConnectionFailure(true)
            connectTimeout(15, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }
        // If you need a custom OkHttpClient:
        // preconfigured = OkHttpClient.Builder()
        //     .build()
    }
}
