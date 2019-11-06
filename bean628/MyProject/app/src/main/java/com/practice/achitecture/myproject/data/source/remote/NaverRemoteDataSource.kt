package com.practice.achitecture.myproject.data.source.remote

import com.practice.achitecture.myproject.model.SearchedItem

interface NaverRemoteDataSource {

    interface GetResultOfSearchingCallBack {
        fun onSuccess(items: List<SearchedItem>)
        fun onSuccessButEmptyData()
        fun onFailure(errorMsg: String)
    }

    fun searchWordByNaver(
        category: String,
        word: String,
        callBack: GetResultOfSearchingCallBack
    )

}