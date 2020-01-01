package com.practice.achitecture.myproject.main

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.base.BaseNaverSearchViewModel
import com.practice.achitecture.myproject.data.source.NaverDataSource
import com.practice.achitecture.myproject.data.source.NaverRepository
import com.practice.achitecture.myproject.enums.SearchType
import com.practice.achitecture.myproject.model.SearchedItem
import kotlin.properties.Delegates

class MainViewModel constructor(private val naverRepository: NaverRepository) :
    BaseNaverSearchViewModel() {

    var query: String by Delegates.observable("") { property, oldValue, newValue -> }

    var queryEmptyObserver: (() -> Unit)? = null

    var goToHistoryActivityObserver: (() -> Unit)? = null


    fun search(searchType: SearchType) {
        lastSearchType = searchType
        search()
    }

    fun search() {
        if (query.isEmpty()) {
            queryEmptyObserver?.invoke()
        } else {
            progressBarIsShowing = true
            naverRepository.searchWordByNaver(
                lastSearchType,
                query,
                object : NaverDataSource.GettingResultOfSearchingCallback {
                    override fun onSuccess(items: List<SearchedItem>) {
                        progressBarIsShowing = false
                        when (lastSearchType) {
                            SearchType.MOVIE, SearchType.BOOK -> {
                                this@MainViewModel.movieOrBookItems = items
                            }
                            SearchType.BLOG, SearchType.NEWS -> {
                                this@MainViewModel.blogOrNewsItems = items
                            }
                        }
                    }

                    override fun onFailure(throwable: Throwable) {
                        progressBarIsShowing = false
                        stringMessageId = R.string.toast_network_error_msg
                    }
                })
        }
    }

    fun goToHistoryActivity() {
        goToHistoryActivityObserver?.invoke()
    }

    fun onEditorAction(tv: TextView, actionId: Int, event: KeyEvent?): Boolean {
        when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> {
                search()
                return true
            }
        }
        return false
    }

    fun loadCache() {
        val lastSearchType = naverRepository.getLastSearchType()
        if (lastSearchType != null) {
            when (lastSearchType) {
                SearchType.MOVIE, SearchType.BOOK -> {
                    movieOrBookItems = naverRepository.getCache(lastSearchType)
                }
                SearchType.BLOG, SearchType.NEWS -> {
                    blogOrNewsItems = naverRepository.getCache(lastSearchType)
                }
            }
        }
    }

}