package org.example.project.data.repository

import org.example.project.data.remote.datasource.CategoryRemoteDataSource
import org.example.project.domain.model.Category

class CategoryRepository(
    private val remote: CategoryRemoteDataSource
) {
    suspend fun getCategories(): List<Category> {
        return try {
            remote.getCategories().map {
                Category(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    createdBy = it.createdBy,
                    createdAt = it.createdAt,
                    updatedBy = it.updatedBy,
                    updatedAt = it.updatedAt
                )
            }
        } catch (e: Exception) {
            println("Gagal fetch category dari remote: ${e.message}")
            emptyList() // fallback
        }
    }
}