package com.example.archstudy.ui.main

import com.example.archstudy.MovieDataResponse
import com.example.archstudy.data.source.local.MovieData

interface MainInterface {

    interface View {
        fun showErrorMessage(msg : Throwable)
        fun showDataList(dataList : MutableList<MovieData>)
    }

    interface Presenter {

        fun getRemoteDatabyQuery(query : String?) : MutableList<MovieDataResponse>?
        fun getLocalQuery() : String?
        fun getLocalData(query: String?) : MutableList<MovieData>?
    }
}