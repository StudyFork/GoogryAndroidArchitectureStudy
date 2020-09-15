package com.example.aas.ui.main

import com.example.aas.base.BaseContract
import com.example.aas.data.model.ApiResult
import io.reactivex.Single

interface MainContract {
    interface View : BaseContract.View {
        fun onSearchRequest()
    }

    interface Presenter : BaseContract.Presenter {
        fun getMovies(query: String): Single<ApiResult>
        fun getSavedQueries(): Single<List<String>>
    }
}