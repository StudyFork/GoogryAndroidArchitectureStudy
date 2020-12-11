package com.example.studyfork.main

import androidx.lifecycle.MutableLiveData
import com.example.studyfork.base.BaseViewModel
import com.example.studyfork.data.model.MovieSearchResponse
import com.example.studyfork.data.repository.Repository
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject

class MainViewModel(private val repository: Repository) : BaseViewModel() {

    val query: MutableLiveData<String> = MutableLiveData()
    val movieList: MutableLiveData<List<MovieSearchResponse.MovieItem>> = MutableLiveData()
    val showError: PublishSubject<Unit> = PublishSubject.create()
    val showQueryError: PublishSubject<Unit> = PublishSubject.create()
    val showResultEmpty: PublishSubject<Unit> = PublishSubject.create()
    val showRecentSearchActivity: PublishSubject<Unit> = PublishSubject.create()

    fun searchMovie() {
        if (this.query.value.isNullOrEmpty()) {
            showQueryError.onNext(Unit)
            return
        } else {
            val query = this.query.value!!
            repository.putRecentSearchList(query)

            repository.searchMovie(
                query = query,
                success = {
                    if (it.items.isEmpty()) {
                        showResultEmpty.onNext(Unit)
                    } else {
                        movieList.value = it.items
                    }
                },
                fail = {
                    showError.onNext(Unit)
                }
            ).addTo(compositeDisposable)
        }
    }

    fun requestRecentSearchActivity() {
        showRecentSearchActivity.onNext(Unit)
    }
}