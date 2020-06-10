package com.lllccww.studyforkproject.ui.main

import androidx.databinding.ObservableField
import com.lllccww.studyforkproject.data.model.MovieItem
import com.lllccww.studyforkproject.data.repository.NaverMovieRepository

class MainViewModel(private val movieRepository: NaverMovieRepository) {
    val movieItemList = ObservableField<ArrayList<MovieItem>>()
    val query = ObservableField<String>()
    val toastString = ObservableField<String>()

    fun getSearchMovie() {
        val inputQuery = query.get()

        if (inputQuery.isNullOrBlank()) {
            toastString.set("검색어를 입력해주세요.")
            return

        }

        movieRepository.getSearchMovie(
            inputQuery,
            success = { movieItem ->
                if (movieItem.isNullOrEmpty()) {
                    toastString.set("검색결과가 없습니다.")
                } else {
                    movieItemList.set(movieItem as ArrayList<MovieItem>)
                }
            },
            failure = {
                toastString.set(it.message.toString())
            }
        )

    }


}