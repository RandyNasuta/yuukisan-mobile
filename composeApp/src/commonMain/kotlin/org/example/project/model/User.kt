package org.example.project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val roleId: Long,
    val username: String,
    val email: String,
    @SerialName("email_verified_at")
    val emailVerifiedAt: String,
    val password: String,
    @SerialName("photo_profile")
    val photoProfile: String,
    val bio: String,
    @SerialName("last_login_at")
    val lastLoginAt: String,
    @SerialName("remember_token")
    val rememberToken: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_by")
    val updatedAt: String
)
