package org.example.project.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import org.example.project.dataClass.ErrorResponse
import org.example.project.dataClass.SuccessResponse
import org.example.project.model.Article
import org.example.project.model.Category

class ApiService(private val client: HttpClient, isMobile: Boolean = true) {

    private val BASE_URL = if (isMobile) "http://10.0.2.2:3000/api" else "http://localhost:3000/api"

    suspend fun getArticles(): List<Article> {
        return try {

            val response: SuccessResponse<List<Article>> = client.get("$BASE_URL/articles").body()

            response.data ?: emptyList()
        } catch (e: ClientRequestException) {
            val errorBody = e.response.bodyAsText()
            val errorResponse = Json.decodeFromString<ErrorResponse>(errorBody)
            println("Error: ${errorResponse.message}")
            throw e
        }
    }

    suspend fun getCategories(): List<Category> {
        return try {
            val response: SuccessResponse<List<Category>> = client.get("$BASE_URL/categories").body()
            response.data ?: emptyList()
        } catch (e: ClientRequestException) {
            val errorBody = e.response.bodyAsText()
            val errorResponse = Json.decodeFromString<ErrorResponse>(errorBody)
            println("Error: ${errorResponse.message}")
            throw e
        }
    }
}