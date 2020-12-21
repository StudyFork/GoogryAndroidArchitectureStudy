package com.example.studyfork.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.example.studyfork.base.BaseViewModel
import com.example.studyfork.data.model.MovieSearchResponse
import com.example.studyfork.data.repository.Repository
import io.reactivex.rxkotlin.addTo

class MainViewModel @ViewModelInject constructor(
    private val repository: Repository
) : BaseViewModel() {

    val query: MutableLiveData<String> = MutableLiveData()
    val movieList: MutableLiveData<List<MovieSearchResponse.MovieItem>> = MutableLiveData()
    val showError: MutableLiveData<Unit> = MutableLiveData()
    val showQueryError: MutableLiveData<Unit> = MutableLiveData()
    val showResultEmpty: MutableLiveData<Unit> = MutableLiveData()
    val showRecentSearchActivity: MutableLiveData<Unit> = MutableLiveData()

    fun searchMovie() {
        if (this.query.value.isNullOrEmpty()) {
            showQueryError.value = Unit
            return
        } else {
            val query = this.query.value!!
            repository.putRecentSearchList(query)

            repository.searchMovie(
                query = query,
                success = {
                    if (it.items.isEmpty()) {
                        showResultEmpty.value = Unit
                    } else {
                        movieList.value = it.items
                    }
                },
                fail = {
                    showError.value = Unit
                }
            ).addTo(compositeDisposable)
        }
    }

    fun requestRecentSearchActivity() {
        showRecentSearchActivity.value = Unit
    }
}