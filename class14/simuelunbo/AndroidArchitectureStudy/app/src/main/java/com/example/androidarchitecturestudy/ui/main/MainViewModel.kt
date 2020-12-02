package com.example.androidarchitecturestudy.ui.main

import androidx.databinding.ObservableField
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.repository.NaverRepository

class MainViewModel(private val repository: NaverRepository) {
    val searchText = ObservableField<String>()
    val movieList = ObservableField<ArrayList<Movie>>()
    val msg = ObservableField<String>()
    val isVisible = ObservableField<Boolean>()
    val keyboard = ObservableField<Unit>()
    val dialog = ObservableField<Unit>()

    fun requestMovieInfo() {
        keyboard.notifyChange()
        if (searchText.get().isNullOrEmpty()) {
            msg.set("검색어를 입력해주세요")
        } else {
            isVisible.set(true)
            repository.getSearchMovieList(searchText.get()!!,
                {
                    isVisible.set(false)
                    movieList.set(it.items as ArrayList<Movie>)
                    repository.saveMovieData(it.items)
                },
                {
                    isVisible.set(false)
                    msg.set(it)
                }
            )
        }
    }

    fun showHistory() {
        dialog.notifyChange()
    }

    fun requestLocalMovieData(){
        repository.getMovieData()
            ?.let { movie -> movieList.set(movie as ArrayList<Movie>) }
    }

}