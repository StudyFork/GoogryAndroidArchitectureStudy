package com.example.study.ui.main

import androidx.databinding.ObservableField
import com.example.study.data.model.Movie
import com.example.study.data.repository.NaverSearchRepository
import com.example.study.util.base.BaseViewModel
import com.example.study.util.extension.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val naverSearchRepository: NaverSearchRepository) : BaseViewModel() {
    var movieItems = ObservableField<List<Movie>>()
    var queryObservableField = ObservableField<String>()
    var isProgressBoolean = ObservableField<Boolean>()
    var isKeyboardBoolean = ObservableField<Boolean>()
    var errorQueryEmpty = ObservableField<Throwable>()
    var errorResultEmpty = ObservableField<Throwable>()
    var errorFailSearch = ObservableField<Throwable>()

    fun getMovies() {
        val queryString: String? = queryObservableField.get()

        if (queryString.isNullOrBlank()) {
            errorQueryEmpty.set(Throwable())
        } else {
            compositeDisposable += (naverSearchRepository.getMovies(queryString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    isProgressBoolean.set(true)
                }
                .doAfterTerminate {
                    isProgressBoolean.set(false)
                    isKeyboardBoolean.set(false)
                }
                .subscribe({
                    it?.let {
                        if (it.items.isNotEmpty()) {
                            movieItems.set(it.items)
                        } else {
                            //view.showErrorEmptyResult()
                            errorResultEmpty.set(Throwable())
                        }
                    }
                }, {
                    it.printStackTrace()
                    errorFailSearch.set(it)
                })
                    )
        }

    }

    fun getRecentSearchResult() {
        naverSearchRepository.getRecentSearchResult()?.let {
            movieItems.set(it)
        }
    }
}