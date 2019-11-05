package com.ironelder.androidarchitecture.data.source

import com.ironelder.androidarchitecture.data.TotalModel

interface ISearchDataSrc<T, R> {
    fun getDataForSearch(
        type: String,
        query: String,
        result:RequestCallback
    )

    interface RequestCallback{
        fun onSuccess(result:TotalModel)
        fun onFail(error:String)
    }
}