package com.studyfork.architecturestudy.ui.main

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.studyfork.architecturestudy.R
import com.studyfork.architecturestudy.base.BaseViewModel
import com.studyfork.architecturestudy.common.ResourceProvider
import com.studyfork.architecturestudy.data.model.MovieResponse
import com.studyfork.architecturestudy.data.repository.MovieRepositoryImpl
import com.studyfork.architecturestudy.data.source.remote.MovieRemoteDataSourceImpl

class MainViewModel(private val resourceProvider: ResourceProvider) : BaseViewModel() {

    private val movieRepositoryImpl: MovieRepositoryImpl by lazy {
        MovieRepositoryImpl(MovieRemoteDataSourceImpl())
    }

    var movieDataList: ObservableField<List<MovieResponse.Item>> = ObservableField()
    var searchText: ObservableField<String> = ObservableField()

    var isLoading: ObservableBoolean = ObservableBoolean()

    fun getMovieList() {
        val query: String? = searchText.get()

        if (query.isNullOrEmpty()) {
            showToast(resourceProvider.getString(R.string.empty_query_notice))
            return
        } else {
            movieRepositoryImpl.getMovieList(query, {
                isLoading.set(it)
            }, {
                if (it.total != 0) {
                    movieDataList.set(it.items)
                } else {
                    showToast(resourceProvider.getString(R.string.empty_data_notice))
                }
            }, {
                it.printStackTrace()
            })
        }
    }
}
