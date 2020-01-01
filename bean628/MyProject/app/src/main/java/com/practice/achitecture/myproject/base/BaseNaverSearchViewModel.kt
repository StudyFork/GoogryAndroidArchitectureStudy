package com.practice.achitecture.myproject.base

import com.practice.achitecture.myproject.enums.SearchType
import com.practice.achitecture.myproject.model.SearchedItem
import kotlin.properties.Delegates

open class BaseNaverSearchViewModel : BaseViewModel() {

    var lastSearchType: SearchType = SearchType.MOVIE

    var movieOrBookItems: List<SearchedItem> by Delegates.observable(arrayListOf()) { property, oldValue, newValue ->
        movieOrBookItemsObserver?.invoke(newValue)
    }
    var movieOrBookItemsObserver: ((List<SearchedItem>) -> Unit)? = null

    var blogOrNewsItems: List<SearchedItem> by Delegates.observable(arrayListOf()) { property, oldValue, newValue ->
        blogOrNewsItemsObserver?.invoke(newValue)
    }
    var blogOrNewsItemsObserver: ((List<SearchedItem>) -> Unit)? = null

}