package com.hongwui.nyt.model

data class ArticleDetails(
    val headline: Headline?,
    val pub_date: String?,
    val title: String?,
    val published_date: String?
)

data class Headline(
    val main: String
)