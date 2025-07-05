package org.example.project.data.repository

import org.example.project.data.remote.datasource.ArticleRemoteDataSource
import org.example.project.domain.model.Article

class ArticleRepository (
    private val remote: ArticleRemoteDataSource
) {
    suspend fun getArticles() : List<Article> {
        return try {
            remote.getArticles().map {
                Article(
                    id = it.id,
                    categoryId = it.categoryId,
                    title = it.title,
                    slug = it.slug,
                    content = it.content,
                    cover = it.cover,
                    imageBase64 = it.imageBase64,
                    writer = it.writer,
                    createdBy = it.createdBy,
                    createdAt = it.createdAt,
                    updatedBy = it.updatedBy,
                    updatedAt = it.updatedAt
                )
            }
        } catch (e: Exception) {
            println("Gagal fetch artikel dari remote: ${e.message}")
            emptyList() // fallback
        }
    }
}