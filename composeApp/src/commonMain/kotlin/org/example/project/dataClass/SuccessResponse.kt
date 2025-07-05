package org.example.project.dataClass

import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null
)
