package com.example.study.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.study.data.model.Movie
import com.example.study.data.repository.NaverSearchRepository
import com.example.study.util.base.BaseViewModel
import com.example.study.util.extension.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val naverSearchRepository: NaverSearchRepository) : BaseViewModel() {
    val movieItems = MutableLiveData<List<Movie>>()
    val queryLiveData = MutableLiveData<String>()
    val isProgressBoolean = MutableLiveData<Boolean>()
    val isKeyboardBoolean = MutableLiveData<Boolean>()
    val errorQueryEmpty = MutableLiveData<Throwable>()
    val errorResultEmpty = MutableLiveData<Throwable>()
    val errorFailSearch = MutableLiveData<Throwable>()

    fun getMovies() {
        val queryString: String? = queryLiveData.value

        if (queryString.isNullOrBlank()) {
            errorQueryEmpty.value = Throwable()
        } else {
            compositeDisposable += (naverSearchRepository.getMovies(queryString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    isProgressBoolean.value = true
                }
                .doAfterTerminate {
                    isProgressBoolean.value = false
                    isKeyboardBoolean.value = false
                }
                .subscribe({
                    it?.let {
                        if (it.items.isNotEmpty()) {
                            movieItems.value = it.items
                        } else {
                            //view.showErrorEmptyResult()
                            errorResultEmpty.value = Throwable()
                        }
                    }
                }, {
                    it.printStackTrace()
                    errorFailSearch.value = it
                })
                    )
        }

    }

    fun getRecentSearchResult() {
        naverSearchRepository.getRecentSearchResult()?.let {
            movieItems.value = it
        }
    }
}