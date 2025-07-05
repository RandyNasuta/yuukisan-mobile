package org.example.project.domain.model

data class Article(
    val id: Long,
    val categoryId: Long,
    val title: String,
    val slug: String,
    val content: String,
    val cover: String,
    val imageBase64: String,
    val writer: String,
    val createdBy: Long,
    val createdAt: String,
    val updatedBy: Long,
    val updatedAt: String
)
