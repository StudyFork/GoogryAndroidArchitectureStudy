package com.practice.achitecture.myproject.base

import androidx.lifecycle.MutableLiveData
import com.practice.achitecture.myproject.enums.SearchType
import com.practice.achitecture.myproject.model.SearchedItem

open class BaseNaverSearchViewModel : BaseViewModel() {

    var lastSearchType = MutableLiveData<SearchType>(SearchType.MOVIE)
    var movieOrBookItems = MutableLiveData<List<SearchedItem>>(emptyList())
    var blogOrNewsItems = MutableLiveData<List<SearchedItem>>(emptyList())

}