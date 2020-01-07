package com.onit.googlearchitecturestudy.ui.main

import android.util.Log
import com.onit.googlearchitecturestudy.data.repository.MovieRepository
import com.onit.googlearchitecturestudy.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val movieRepositroy: MovieRepository = MovieRepositoryImpl()

    override fun searchMovies(keyword: String) {
        view.hideKeyBoard()

        CoroutineScope(Dispatchers.Main).launch {
            view.showLoadingProgressBar()

            val response =
                withContext(Dispatchers.IO) { movieRepositroy.getMovieList(keyword) }

            if (response.isSuccessful) {
                val movieList = response.body()?.movies
                if (movieList == null) {
                    view.showToastMessage("검색결과가 없습니다.")
                } else {
                    view.setMovieList(movieList)
                }
            } else {
                Log.e("MainActivity", "네이버 API요청에 실패했습니다. responseCode = ${response.code()}")
                view.showToastMessage("네트워크가 불안정합니다.")
            }

            view.hideLoadingProgressBar()
        }
    }
}