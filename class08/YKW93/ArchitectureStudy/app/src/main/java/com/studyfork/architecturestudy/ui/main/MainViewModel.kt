package com.studyfork.architecturestudy.ui.main

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.studyfork.architecturestudy.base.BaseViewModel
import com.studyfork.architecturestudy.data.model.MovieResponse
import com.studyfork.architecturestudy.data.repository.MovieRepositoryImpl
import com.studyfork.architecturestudy.data.source.remote.MovieRemoteDataSourceImpl

class MainViewModel : BaseViewModel() {

    private val movieRepositoryImpl: MovieRepositoryImpl by lazy {
        MovieRepositoryImpl(MovieRemoteDataSourceImpl())
    }

    var movieDataList: ObservableField<List<MovieResponse.Item>> = ObservableField()
    var searchText: ObservableField<String> = ObservableField()

    var isLoading: ObservableBoolean = ObservableBoolean()
    var isErrorEmptyQuery: ObservableBoolean = ObservableBoolean()
    var isErrorEmptyResult: ObservableBoolean = ObservableBoolean()

    fun getMovieList() {
        val query: String? = searchText.get()

        if (query.isNullOrEmpty()) {
            isErrorEmptyQuery.set(true)
            return
        } else {
            movieRepositoryImpl.getMovieList(query, {
                isLoading.set(it)
            }, {
                if (it.total != 0) {
                    movieDataList.set(it.items)
                } else {
                    isErrorEmptyResult.set(true)
                }
            }, {
                it.printStackTrace()
            })
        }
    }
}
