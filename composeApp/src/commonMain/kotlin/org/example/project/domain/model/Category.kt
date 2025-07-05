package org.example.project.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Category(
    val id: Long,
    val name: String,
    val description: String,
    val createdBy: Long,
    val createdAt: String,
    val updatedBy: Long,
    val updatedAt: String
)
