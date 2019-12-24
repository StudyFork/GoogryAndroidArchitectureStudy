package com.practice.achitecture.myproject.data.source

import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.model.SearchedItem

interface NaverDataSource {

    interface GettingResultOfSearchingCallBack {
        fun onSuccess(items: List<SearchedItem>)
        fun onFailure(throwable: Throwable)
    }

    fun searchWordByNaver(
        searchType: SearchType,
        word: String,
        callBack: GettingResultOfSearchingCallBack
    )

}