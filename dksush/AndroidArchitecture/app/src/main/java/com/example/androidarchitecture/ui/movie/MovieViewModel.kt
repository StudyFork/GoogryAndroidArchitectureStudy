package com.example.androidarchitecture.ui.movie

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.MovieData

class MovieViewModel(private val naverRepositroy: NaverRepoInterface) {

    private val _renderItems = MutableLiveData<List<MovieData>>()
    val renderItems:LiveData<List<MovieData>> get() = _renderItems

    val isListEmpty = MutableLiveData<Boolean>(true)
    val blankInputText = MutableLiveData<Unit>()
    val errorToast = MutableLiveData<Throwable>()
    var inputKeyword = naverRepositroy.getMoiveKeyword()

    suspend fun requestSearchHist() {
        naverRepositroy.getMovieHist().let {
            if (it.isNotEmpty()) {
                isListEmpty.value = it.isEmpty()
                _renderItems.value = it

            }
        }

    }


    fun onBtnSearch() {
        if (TextUtils.isEmpty(inputKeyword)) {
            blankInputText.value = Unit
        } else {
            naverRepositroy.saveMovieKeyword(inputKeyword) // 검색어 쉐어드에 저장.
            naverRepositroy.getMovie(inputKeyword, 1, 10,
                success = {
                    isListEmpty.value = false
                    _renderItems.value = it
                }, fail = {
                    errorToast.value = it
                })
        }
    }
}