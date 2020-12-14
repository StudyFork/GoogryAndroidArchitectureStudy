package com.example.studyfork.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studyfork.base.BaseViewModel
import com.example.studyfork.data.model.MovieSearchResponse
import com.example.studyfork.data.repository.Repository
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject

class MainViewModel(private val repository: Repository) : BaseViewModel() {

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

class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(repository = repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }

}