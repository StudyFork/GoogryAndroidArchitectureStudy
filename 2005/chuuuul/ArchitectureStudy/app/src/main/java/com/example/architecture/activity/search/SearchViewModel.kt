package com.example.architecture.activity.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecture.R
import com.example.architecture.data.model.MovieModel
import com.example.architecture.data.repository.NaverRepositoryImpl
import com.example.architecture.ext.createDefault
import com.example.architecture.provider.ResourceProviderImpl

class SearchViewModel(
    private val naverRepository: NaverRepositoryImpl,
    private val resourceProvider: ResourceProviderImpl
) : ViewModel() {

    val keyword = MutableLiveData<String>().createDefault("")

    private val _isLoading = MutableLiveData<Boolean>().createDefault(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _movieList = MutableLiveData<MutableList<MovieModel>>().createDefault(mutableListOf())
    val movieList: LiveData<MutableList<MovieModel>> = _movieList

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    val searchEvent = MutableLiveData<Unit>()
    val onClickEvent = MutableLiveData<Unit>()

    fun searchMovie() {
        keyword.value?.let { keyword ->
            if (isValidKeyword(keyword)) {
                setVisibleProgressBar(true)
                naverRepository.getMovieList(keyword, this::onSuccess, this::onFailure)
            }
        }
    }

    private fun isValidKeyword(keyword: String): Boolean {

        return if (keyword.isBlank()) {
            _toastMessage.value = resourceProvider.getString(R.string.empty_keyword)
            false
        } else {
            true
        }
    }

    private fun onSuccess(movieList: List<MovieModel>) {
        if (movieList.isNotEmpty()) {
            _movieList.value?.also {
                it.clear()
                it.addAll(movieList)
                _movieList.value = it
            }
        } else {
            _toastMessage.value = resourceProvider.getString(R.string.not_found_result)
        }
        setVisibleProgressBar(false)
    }

    private fun onFailure(t: Throwable) {
        Log.d("chul", "OnFailure : $t")
        setVisibleProgressBar(false)
    }

    private fun setVisibleProgressBar(visible: Boolean) {
        _isLoading.value = visible
    }

    fun onSearchClick() {
        onClickEvent.value = Unit
    }

    private fun clearCacheData(keyword: String) {
        naverRepository.clearCacheData()
    }

}