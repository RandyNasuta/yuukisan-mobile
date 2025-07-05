package org.example.project.ui.screen.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.data.repository.ArticleRepository
import org.example.project.data.repository.CategoryRepository
import org.example.project.domain.model.Article
import org.example.project.domain.model.Category

class ArticleViewModel (
    private val articleRepository: ArticleRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _articles.value = articleRepository.getArticles()
                _categories.value = categoryRepository.getCategories()
            } catch (e: Exception) {
                _error.value = e.message ?: "Terjadi kesalahan"
            } finally {
                _isLoading.value = false
            }
        }
    }
}