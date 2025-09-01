package org.example.project.dashboard.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val language: String,
    val id: String,
    val bio: String,
    val version: Double,
    val profilePicture: String? =null
)
