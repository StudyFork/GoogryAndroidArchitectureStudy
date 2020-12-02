package com.architecture.androidarchitecturestudy.ui.main


import androidx.databinding.ObservableField
import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.data.repository.MovieRepositoryImpl

class MainViewModel(private val movieRepository: MovieRepositoryImpl) {

    val keyword = ObservableField<String>()
    val movieList = ObservableField<List<Movie>>()
    val onFailedEvent = ObservableField<Unit>()
    val onEmptyQuery = ObservableField<Unit>()
    val onEmptyResult = ObservableField<Unit>()

    fun findMovie() {
        if (keyword.get().isNullOrBlank()) {
            onEmptyQuery.notifyChange()
        }
        movieRepository.getMovieData(keyword = keyword.get()!!, 30,
            onSuccess = {
                if (it.items!!.isEmpty()) {
                    onEmptyResult.notifyChange()
                } else {
                    movieList.set(it.items)
                }
            },
            onFailure = { onFailedEvent.notifyChange() }
        )
    }
}