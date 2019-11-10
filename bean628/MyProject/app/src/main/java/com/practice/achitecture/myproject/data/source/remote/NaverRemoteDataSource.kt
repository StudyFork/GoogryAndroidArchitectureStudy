package com.practice.achitecture.myproject.data.source.remote

import com.practice.achitecture.myproject.model.SearchedItem

interface NaverRemoteDataSource {

    interface GettingResultOfSearchingCallBack {
        fun onSuccess(items: List<SearchedItem>)
        fun onFailure(throwable: Throwable)
    }

    fun searchWordByNaver(
        category: String,
        word: String,
        callBack: GettingResultOfSearchingCallBack
    )

}