package org.example.project.data.remote.datasource

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import org.example.project.data.remote.model.ArticleResponse
import org.example.project.data.remote.model.ErrorResponse
import org.example.project.data.remote.model.SuccessResponse
import org.example.project.service.ApiService

class ArticleRemoteDataSource(private val apiService: ApiService) {
    suspend fun getArticles(): List<ArticleResponse> {
        return try {

            val response: SuccessResponse<List<ArticleResponse>> = apiService.client.get("${apiService.BASE_URL}/articles").body()

            response.data ?: emptyList()
        } catch (e: ClientRequestException) {
            val errorBody = e.response.bodyAsText()
            val errorResponse = Json.decodeFromString<ErrorResponse>(errorBody)
            println("Error: ${errorResponse.message}")
            throw e
        }
    }
}