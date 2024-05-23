package com.hongwui.nyt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hongwui.nyt.base.ApiResult
import com.hongwui.nyt.domain.NytMapper
import com.hongwui.nyt.domain.NytRepository
import com.hongwui.nyt.model.ArticleModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ArticleListViewModel(
    private val repository: NytRepository
) : ViewModel() {

    private val _isLoading = MutableSharedFlow<Boolean>()
    val isLoading: SharedFlow<Boolean> = _isLoading

    private val _articleList = MutableSharedFlow<List<ArticleModel>>()
    val articleList: SharedFlow<List<ArticleModel>> = _articleList

    private val _error = MutableSharedFlow<String>()
    val error: SharedFlow<String> = _error

    fun searchArticle(keyword: String) {
        viewModelScope.launch {
            _isLoading.emit(true)
            repository.searchArticle(keyword).runCatching {
                collect {
                    when (it) {
                        is ApiResult.Success -> {
                            viewModelScope.launch {
                                _isLoading.emit(false)
                                _articleList.emit(NytMapper().mapFromSearch(it.body!!.response!!.docs))
                            }
                        }

                        is ApiResult.Error -> {
                            viewModelScope.launch {
                                _isLoading.emit(false)
                                _error.emit(it.message!!)
                            }
                        }
                    }
                }
            }
        }
    }

    fun getPopularArticle(type: String) {
        viewModelScope.launch {
            _isLoading.emit(true)
            repository.getMostPopularArticleByType(type).runCatching {
                collect {
                    when (it) {
                        is ApiResult.Success -> {
                            viewModelScope.launch {
                                _isLoading.emit(false)
                                _articleList.emit(NytMapper().mapFromMost(it.body!!.results!!))
                            }
                        }

                        is ApiResult.Error -> {
                            viewModelScope.launch {
                                _isLoading.emit(false)
                                _error.emit(it.message!!)
                            }
                        }
                    }
                }
            }
        }
    }
}