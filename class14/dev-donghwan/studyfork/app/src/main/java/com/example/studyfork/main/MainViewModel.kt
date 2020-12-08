package com.example.studyfork.main

import androidx.databinding.ObservableField
import com.example.studyfork.base.BaseViewModel
import com.example.studyfork.data.model.MovieSearchResponse
import com.example.studyfork.data.repository.Repository
import io.reactivex.rxkotlin.addTo

class MainViewModel(private val repository: Repository) : BaseViewModel() {

    val movieList: ObservableField<List<MovieSearchResponse.MovieItem>> = ObservableField()
    val showError: ObservableField<Unit> = ObservableField()
    val showQueryError: ObservableField<Unit> = ObservableField()
    val showResultEmpty: ObservableField<Unit> = ObservableField()

    fun searchMovie(query: String) {
        if (query.isNullOrEmpty()) {
            showQueryError.set(Unit)
            return
        }

        repository.putRecentSearchList(query)

        repository.searchMovie(
            query = query,
            success = {
                if (it.items.isEmpty()) {
                    showResultEmpty.set(Unit)
                } else {
                    movieList.set(it.items)
                }
                movieList.notifyChange()
            },
            fail = {
                showError.set(Unit)
            }
        ).addTo(compositeDisposable)
    }

    fun clear() {
        compositeDisposable.clear()
    }
}