package com.example.study.presenter

import com.example.study.data.model.NaverApiData

interface MovieContract {
    interface View {
        fun showMovieList(naverApiData: List<NaverApiData.Item>)
        fun showErrorResponse(t: Throwable)
        fun showQueryEmpty()
    }

    interface Presenter {
        fun requestMovieList(query: String)
    }
}