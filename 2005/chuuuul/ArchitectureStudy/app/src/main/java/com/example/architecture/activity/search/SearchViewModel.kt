package com.example.architecture.activity.search

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.example.architecture.data.model.MovieModel
import com.example.architecture.data.repository.NaverRepositoryImpl

class SearchViewModel(private val naverRepository: NaverRepositoryImpl) {

    val keyword = ObservableField<String>("")
    val isLoading = ObservableBoolean(false)
    val movieList = ObservableArrayList<MovieModel>()
    val showMessageEmptyKeyword = ObservableField<Unit>()
    val showMessageEmptyResult = ObservableField<Unit>()

    fun searchMovie() {
        keyword.get()?.let { keyword ->
            if (isValidKeyword(keyword)) {
                showProgressBar(true)
                naverRepository.getMovieList(keyword, this::onSuccess, this::onFailure)
            }
        }
    }

    private fun isValidKeyword(keyword: String): Boolean {

        return if (keyword.isBlank()) {
            showMessageEmptyKeyword.notifyChange()
            false
        } else {
            true
        }
    }

    private fun onSuccess(movieList: List<MovieModel>) {
        if (movieList.isNotEmpty()) {
            this.movieList.clear()
            this.movieList.addAll(movieList)
        } else {
            showMessageEmptyResult.notifyChange()
        }
        showProgressBar(false)
    }

    private fun onFailure(t: Throwable) {
        Log.d("chul", "OnFailure : $t")
        showProgressBar(false)
    }

    private fun showProgressBar(visible: Boolean) {
        isLoading.set(visible)
    }

    private fun clearCacheData(keyword: String) {
        naverRepository.clearCacheData()
    }

}