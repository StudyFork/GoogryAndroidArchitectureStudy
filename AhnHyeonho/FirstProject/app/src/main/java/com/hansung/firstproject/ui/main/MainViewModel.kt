package com.hansung.firstproject.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hansung.firstproject.data.MovieModel
import com.hansung.firstproject.data.repository.NaverRepository

class MainViewModel(private val repository: NaverRepository) : ViewModel() {

    private val _isEmptyResult = MutableLiveData<Throwable>()
    private val _showKeywordEmptyError = MutableLiveData<Throwable>()
    private val _hideKeyboard = MutableLiveData<Throwable>()

    val movieData: MutableLiveData<ArrayList<MovieModel>> by lazy { MutableLiveData<ArrayList<MovieModel>>() }
    val keyword: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val showError: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val isEmptyResult: LiveData<Throwable> get() = _isEmptyResult
    val showKeywordEmptyError: LiveData<Throwable> get() = _showKeywordEmptyError
    val hideKeyboard: LiveData<Throwable> get() = _hideKeyboard


    fun doSearch() {
        if (keyword.value.isNullOrBlank()) {
            _showKeywordEmptyError.value = Throwable()
        } else {
            // 검색 메소드
            repository.getMoviesData(
                keyword.value!!,

                success = {
                    movieData.value = it.items
                },
                fail = {
                    showError.value = it.message
                    Log.d("ahn", showError.value.toString())
                },
                isEmptyList = {
                    _isEmptyResult.value = Throwable()
                }
            )
            _hideKeyboard.value = Throwable()
        }
    }
}