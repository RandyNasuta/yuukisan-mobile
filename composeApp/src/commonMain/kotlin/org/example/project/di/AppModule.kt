package org.example.project.di

import org.example.project.data.remote.datasource.ArticleRemoteDataSource
import org.example.project.data.remote.datasource.CategoryRemoteDataSource
import org.example.project.data.repository.ArticleRepository
import org.example.project.data.repository.CategoryRepository
import org.example.project.getPlatform
import org.example.project.service.ApiService

object AppModule {
    private val apiService = ApiService(createHttpClient(), getPlatform().name.contains("Android"))

    private val ArticleRemote = ArticleRemoteDataSource(apiService)
    val articleRepository = ArticleRepository(ArticleRemote)

    private val CategoryRemote = CategoryRemoteDataSource(apiService)
    val categoryRepository = CategoryRepository(CategoryRemote)

}