package com.example.aas.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aas.base.BaseViewModel
import com.example.aas.data.model.Movie
import com.example.aas.data.repository.MovieSearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val movieSearchRepository: MovieSearchRepository) : BaseViewModel() {
    private val _searchRequestEvent: MutableLiveData<Unit> = MutableLiveData()
    private val _failureEvent: MutableLiveData<Unit> = MutableLiveData()
    private val _movieSearchResult = MutableLiveData<List<Movie>>()
    private val _savedQueryResult = MutableLiveData<Array<String>>()

    val searchRequestEvent: LiveData<Unit> = _searchRequestEvent
    val failureEvent: LiveData<Unit> = _failureEvent
    val movieSearchResult: LiveData<List<Movie>> = _movieSearchResult
    val savedQueryResult: LiveData<Array<String>> = _savedQueryResult

    fun getMovies(query: String) {
        _searchRequestEvent.value = Unit
        movieSearchRepository.getMovies(query)
            .map { it.movies }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _movieSearchResult.value = it
            }, {
                it.printStackTrace()
                _failureEvent.value = Unit
            }).addTo(compositeDisposable)
    }

    fun getSavedQueries() {
        movieSearchRepository.getSavedQueries()
            .observeOn(Schedulers.computation())
            .map { it.reversed().toTypedArray() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _savedQueryResult.value = it
            }, {
                it.printStackTrace()
                _failureEvent.value = Unit
            }).addTo(compositeDisposable)
    }
}