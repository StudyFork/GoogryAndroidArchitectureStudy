package com.practice.achitecture.myproject.base

import androidx.lifecycle.MutableLiveData
import com.practice.achitecture.myproject.enums.SearchType
import com.practice.achitecture.myproject.model.SearchedItem

open class BaseNaverSearchViewModel : BaseViewModel() {

    val lastSearchType = MutableLiveData<SearchType>(SearchType.MOVIE)
    val movieOrBookItems = MutableLiveData<List<SearchedItem>>(emptyList())

}