package com.hongwui.nyt.domain

import com.hongwui.nyt.base.fromDateToDate
import com.hongwui.nyt.model.ArticleDetails
import com.hongwui.nyt.model.ArticleModel

class NytMapper {

    fun mapFromSearch(response: List<ArticleDetails>): List<ArticleModel> {
        val list = mutableListOf<ArticleModel>()
        response.map {
            list.add(
                ArticleModel(
                    it.headline!!.main,
                    it.pub_date!!.fromDateToDate("yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd")
                )
            )
        }
        return list
    }

    fun mapFromMost(response: List<ArticleDetails>): List<ArticleModel> {
        val list = mutableListOf<ArticleModel>()
        response.map {
            list.add(ArticleModel(it.title!!, it.published_date!!))
        }
        return list
    }

}