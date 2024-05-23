package com.hongwui.nyt.model

data class ArticleListResponse (
    val status: String,
    val response: DocumentList? = null,
    val results: List<ArticleDetails>? = null
)

data class DocumentList(
    val docs: List<ArticleDetails>
)