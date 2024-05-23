package com.hongwui.nyt.domain

import com.hongwui.nyt.base.ApiResult
import com.hongwui.nyt.base.BaseApiCall
import com.hongwui.nyt.model.ArticleListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NytRepository : BaseApiCall() {

    suspend fun searchArticle(keyword: String): Flow<ApiResult<ArticleListResponse>> {
        return flow {
            emit(
                makeApiCall {
                    ApiClient.nytService().searchArticle(keyword)
                }
            )
        }
    }

    suspend fun getMostPopularArticleByType(type: String): Flow<ApiResult<ArticleListResponse>> {
        return flow {
            emit(
                makeApiCall {
                    ApiClient.nytService().getMostPopularArticleByType(type)
                }
            )
        }
    }

}