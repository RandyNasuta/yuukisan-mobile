package org.example.project.dataClass

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val success: Boolean,
    val message: String,
    val errors: Map<String, String>
)
