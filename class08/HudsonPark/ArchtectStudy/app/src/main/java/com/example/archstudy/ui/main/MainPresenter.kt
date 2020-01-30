package com.example.archstudy.ui.main

import com.example.archstudy.MovieDataResponse
import com.example.archstudy.data.source.local.MovieData

class MainPresenter : MainInterface.Presenter {
    override fun getRemoteDatabyQuery(query: String?): MutableList<MovieDataResponse>? {

    }

    override fun getLocalQuery(): String? {

    }

    override fun getLocalData(query: String?): MutableList<MovieData>? {

    }

}