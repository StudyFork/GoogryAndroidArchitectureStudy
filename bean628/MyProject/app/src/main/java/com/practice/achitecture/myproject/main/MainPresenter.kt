package com.practice.achitecture.myproject.main

import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSource
import com.practice.achitecture.myproject.data.source.remote.NaverRepository
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.model.SearchedItem


class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {


    override fun searchIfNotEmpty(word: String, searchType: SearchType) {
        if (word.isEmpty()) {
            view.isEmpty()
        } else {
            val category = when (searchType) {
                SearchType.MOVIE -> "movie"
                SearchType.BOOK -> "book"
                SearchType.BLOG -> "blog"
                SearchType.NEWS -> "news"
            }
            this.searchWordByNaver(searchType, category, word)
        }
    }

    override fun searchWordByNaver(searchType: SearchType, category: String, word: String) {
        view.showLoading()
        NaverRepository.searchWordByNaver(
            category,
            word,
            object : NaverRemoteDataSource.GettingResultOfSearchingCallBack {

                override fun onSuccess(items: List<SearchedItem>) {
                    view.hideLoading()
                    when (searchType) {
                        SearchType.MOVIE, SearchType.BOOK -> {
                            view.showSearchResultMovieOrBook(items)
                        }
                        SearchType.BLOG, SearchType.NEWS -> {
                            view.showSearchResultBlogOrNews(items)
                        }
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    view.hideLoading()
                    view.searchingOnFailure(throwable)
                }
            })
    }

}