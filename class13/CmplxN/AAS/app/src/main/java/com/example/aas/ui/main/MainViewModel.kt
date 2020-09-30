package com.example.aas.ui.main

import androidx.databinding.ObservableField
import com.example.aas.base.BaseViewModel
import com.example.aas.data.model.Movie
import com.example.aas.data.repository.MovieSearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val movieSearchRepository: MovieSearchRepository) : BaseViewModel() {
    val searchRequestEvent = ObservableField<Unit>()
    val failureEvent = ObservableField<Unit>()
    val movieSearchResult = ObservableField<List<Movie>>()
    val savedQueryResult = ObservableField<Array<String>>()
    val movieUrl = ObservableField<String>()

    fun getMovies(query: String) {
        searchRequestEvent.notifyChange()
        movieSearchRepository.getMovies(query).map { it.movies }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                movieSearchResult.set(it)
            }, {
                it.printStackTrace()
                failureEvent.notifyChange()
            }).addTo(compositeDisposable)
    }

    fun getSavedQueries() {
        movieSearchRepository.getSavedQueries()
            .observeOn(Schedulers.computation())
            .map { it.reversed().toTypedArray() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                savedQueryResult.set(it)
            }, {
                it.printStackTrace()
                failureEvent.notifyChange()
            }).addTo(compositeDisposable)
    }

    fun openMovieSpecific(url: String) {
        if (movieUrl.get() == url) {
            movieUrl.notifyChange()
        } else {
            movieUrl.set(url)
        }
    }

    override fun onDestroy() {
        movieSearchRepository.onDestroy()
        super.onDestroy()
    }
}