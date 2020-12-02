package com.example.hw2_project.recentSearch

import androidx.databinding.ObservableField
import com.example.hw2_project.data.repository.MovieRepositoryImpl

class RecentSearchViewModel(private val repository: MovieRepositoryImpl) {
    val movieListFromSharedPref = ObservableField<List<String>>()

    val clickedMovie = ObservableField<String>()
    val successToClickRecentMovie = ObservableField<Unit>()
    val backToMainActivity = ObservableField<Unit>()

    fun getRecentMovie() {
        movieListFromSharedPref.set(repository.getSavedQuery())
    }

/*    fun clickSavedMovieList(movie: String) {
        clickedMovie.set(movie)
        clickedMovie.notifyChange()
    }*/

    fun clickBackBtn() {
        backToMainActivity.notifyChange()
    }
}