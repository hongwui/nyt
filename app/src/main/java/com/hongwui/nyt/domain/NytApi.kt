package com.hongwui.nyt.domain

import com.hongwui.nyt.model.ArticleListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NytApi {

    @GET("svc/search/v2/articlesearch.json")
    suspend fun searchArticle(@Query("fq") keyword: String): Response<ArticleListResponse>

    @GET("svc/mostpopular/v2/{type}/1.json")
    suspend fun getMostPopularArticleByType(@Path("type") type: String): Response<ArticleListResponse>
}