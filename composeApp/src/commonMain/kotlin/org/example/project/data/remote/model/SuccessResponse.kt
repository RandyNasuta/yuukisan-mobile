package org.example.project.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null
)
