package com.practice.achitecture.myproject.main

import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSource
import com.practice.achitecture.myproject.data.source.remote.NaverRepository
import com.practice.achitecture.myproject.model.SearchedItem
import common.SEARCH_TYPE_BLOG
import common.SEARCH_TYPE_BOOK
import common.SEARCH_TYPE_MOVIE
import common.SEARCH_TYPE_NEWS


class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {


    override fun searchIfNotEmpty(word: String, searchType: Int) {
        if (word.isEmpty()) {
            view.isEmpty()
        } else {
            val category = when (searchType) {
                SEARCH_TYPE_MOVIE -> "movie"
                SEARCH_TYPE_BOOK -> "book"
                SEARCH_TYPE_BLOG -> "blog"
                SEARCH_TYPE_NEWS -> "news"
                else -> "movie"
            }
            this.searchWordByNaver(searchType, category, word)
        }
    }

    override fun searchWordByNaver(searchType: Int, category: String, word: String) {
        NaverRepository.searchWordByNaver(
            category,
            word,
            object : NaverRemoteDataSource.GettingResultOfSearchingCallBack {

                override fun onSuccess(items: List<SearchedItem>) {
                    when (searchType) {
                        SEARCH_TYPE_MOVIE, SEARCH_TYPE_BOOK -> {
                            view.showSearchResultMovieOrBook(items)
                        }
                        SEARCH_TYPE_BLOG, SEARCH_TYPE_NEWS -> {
                            view.showSearchResultBlogOrNews(items)
                        }
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    view.searchingOnFailure(throwable)
                }
            })
    }

}