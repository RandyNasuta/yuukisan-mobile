package org.example.project.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val success: Boolean,
    val message: String,
    val errors: Map<String, String>
)
