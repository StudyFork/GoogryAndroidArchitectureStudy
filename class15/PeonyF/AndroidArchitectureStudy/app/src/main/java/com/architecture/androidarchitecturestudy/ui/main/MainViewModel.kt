package com.architecture.androidarchitecturestudy.ui.main


import androidx.databinding.ObservableField
import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.data.repository.MovieRepository

class MainViewModel(private val movieRepository: MovieRepository) {

    val keyword = ObservableField<String>()
    val movieList = ObservableField<List<Movie>>()
    val showToastMsg = ObservableField<Unit>()
    val msg = ObservableField<String>()
    val callSearchHistory = ObservableField<Unit>()

    fun findMovie() {
        val keyword = keyword.get() ?: return
        if (keyword.isNullOrBlank()) {
            msg.set("emptyKeyword")
            showToastMsg.notifyChange()
            return
        }
        movieRepository.getMovieData(keyword = keyword, 30,
            onSuccess = {
                if (it.items!!.isEmpty()) {
                    msg.set("emptyResult")
                    showToastMsg.notifyChange()
                } else {
                    msg.set("success")
                    movieList.set(it.items)
                }
            },
            onFailure = {
                msg.set("fail")
                showToastMsg.notifyChange()
            }
        )
    }

    fun searchHistory() {
        callSearchHistory.notifyChange()
    }
}