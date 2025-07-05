package org.example.project.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import org.example.project.data.remote.model.ErrorResponse
import org.example.project.data.remote.model.SuccessResponse
import org.example.project.domain.model.Article
import org.example.project.domain.model.Category

class ApiService(val client: HttpClient, isMobile: Boolean = true) {
    val BASE_URL = if (isMobile) "http://10.0.2.2:3000/api" else "http://localhost:3000/api"
}