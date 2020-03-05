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

    val _query = MutableLiveData<String>()

    private val _movieItems = MutableLiveData<List<Movie>>()
    val movieItems: LiveData<List<Movie>>
        get() = _movieItems


    private val _isProgressBoolean = MutableLiveData<Boolean>()
    val isProgressBoolean: LiveData<Boolean>
        get() = _isProgressBoolean

    private val _isKeyboardBoolean = MutableLiveData<Boolean>()
    val isKeyboardBoolean: LiveData<Boolean>
        get() = _isKeyboardBoolean

    private val _errorQueryEmpty = MutableLiveData<Throwable>()
    val errorQueryEmpty: LiveData<Throwable>
        get() = _errorQueryEmpty

    private val _errorResultEmpty = MutableLiveData<Throwable>()
    val errorResultEmpty: LiveData<Throwable>
        get() = _errorResultEmpty

    private val _errorFailSearch = MutableLiveData<Throwable>()
    val errorFailSearch: LiveData<Throwable>
        get() = _errorFailSearch

    fun getMovies() {
        val queryString: String? = _query.value

        if (queryString.isNullOrBlank()) {
            _errorQueryEmpty.value = Throwable()
        } else {
            compositeDisposable += (naverSearchRepository.getMovies(queryString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _isProgressBoolean.value = true
                }
                .doAfterTerminate {
                    _isProgressBoolean.value = false
                    _isKeyboardBoolean.value = false
                }
                .subscribe({
                    it?.let {
                        if (it.items.isNotEmpty()) {
                            _movieItems.value = it.items
                        } else {
                            //view.showErrorEmptyResult()
                            _errorResultEmpty.value = Throwable()
                        }
                    }
                }, {
                    it.printStackTrace()
                    _errorFailSearch.value = it
                })
                    )
        }

    }

    fun getRecentSearchResult() {
        naverSearchRepository.getRecentSearchResult()?.let {
            _movieItems.value = it
        }
    }
}