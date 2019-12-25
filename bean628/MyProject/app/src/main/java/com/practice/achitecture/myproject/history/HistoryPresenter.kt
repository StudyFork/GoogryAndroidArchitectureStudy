package com.practice.achitecture.myproject.history

import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.data.source.NaverDataSource
import com.practice.achitecture.myproject.data.source.NaverRepository
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.model.SearchedItem

class HistoryPresenter(
    private val view: HistoryContract.View,
    private val naverRepository: NaverRepository
) : HistoryContract.Presenter {


    override fun loadHistory(searchType: SearchType) {
        naverRepository.loadHistoryOfSearch(
            searchType,
            object : NaverDataSource.LoadHistoryOfSearchCallback {
                override fun onLoadSuccess(items: List<SearchedItem>) {
                    when (searchType) {
                        SearchType.MOVIE, SearchType.BOOK -> {
                            view.showSearchResultMovieOrBook(items)
                        }
                        SearchType.BLOG, SearchType.NEWS -> {
                            view.showSearchResultBlogOrNews(items)
                        }
                    }
                }

                override fun onEmptyData() {
                    when (searchType) {
                        SearchType.MOVIE -> view.historyEmpty(R.string.toast_empty_movie_search_history)
                        SearchType.BOOK -> view.historyEmpty(R.string.toast_empty_book_search_history)
                        SearchType.BLOG -> view.historyEmpty(R.string.toast_empty_blog_search_history)
                        SearchType.NEWS -> view.historyEmpty(R.string.toast_empty_news_search_history)
                    }
                }
            })
    }


}