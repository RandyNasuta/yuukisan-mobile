package org.example.project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Long,
    val name: String,
    val description: String,
    @SerialName("created_by")
    val createdBy: Long,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_by")
    val updatedBy: Long,
    @SerialName("updated_at")
    val updatedAt: String
)
