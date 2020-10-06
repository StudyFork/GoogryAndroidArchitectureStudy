package com.hong.architecturestudy.ui.moviedialog

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.hong.architecturestudy.data.repository.RepositoryDataSource
import com.hong.architecturestudy.data.source.local.entity.MovieInfo

class MovieListDialogViewModel(private val repository: RepositoryDataSource) : ViewModel() {

    val movieInfo = ObservableField<List<MovieInfo>>()

    fun loadRecentSearchMovieList() {
        val list = repository.loadRecentSearchQuery()
        movieInfo.set(list)

    }

}