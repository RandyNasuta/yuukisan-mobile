package org.example.project.data.local.model

data class ArticleEntity(
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