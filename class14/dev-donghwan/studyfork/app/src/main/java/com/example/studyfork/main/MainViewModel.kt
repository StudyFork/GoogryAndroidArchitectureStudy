package com.example.studyfork.main

import androidx.databinding.ObservableField
import com.example.studyfork.base.BaseViewModel
import com.example.studyfork.data.model.MovieSearchResponse
import com.example.studyfork.data.repository.Repository
import io.reactivex.rxkotlin.addTo

class MainViewModel(private val repository: Repository) : BaseViewModel() {

    val query: ObservableField<String> = ObservableField()
    val movieList: ObservableField<List<MovieSearchResponse.MovieItem>> = ObservableField()
    val showError: ObservableField<Unit> = ObservableField()
    val showQueryError: ObservableField<Unit> = ObservableField()
    val showResultEmpty: ObservableField<Unit> = ObservableField()
    val showRecentSearchActivity: ObservableField<Unit> = ObservableField()

    fun searchMovie() {
        if (query.get().isNullOrEmpty()) {
            showQueryError.notifyChange()
            return
        } else {
            val query = query.get()!!
            repository.putRecentSearchList(query)

            repository.searchMovie(
                query = query,
                success = {
                    if (it.items.isEmpty()) {
                        showResultEmpty.notifyChange()
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
    }

    fun requestRecentSearchActivity() {
        showRecentSearchActivity.notifyChange()
    }

    fun clear() {
        compositeDisposable.clear()
    }
}