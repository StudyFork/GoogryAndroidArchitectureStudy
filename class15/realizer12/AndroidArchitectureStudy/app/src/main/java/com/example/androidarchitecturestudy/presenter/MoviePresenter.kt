package com.example.androidarchitecturestudy.presenter

import android.util.Log
import com.example.androidarchitecturestudy.data.repository.MovieRepository

class MoviePresenter(
    private val view: MovieContract.View,
    private val repository: MovieRepository
) : MovieContract.Presenter {

    override fun getMovieData(searchQuery: String) {
        if (searchQuery.isEmpty()) {//검색어가 비어있을때
            view.showSearchQueryEmpty()
        } else {
            //영화 검색 실행
            repository.getMovieSearchResult(searchQuery, {
                it.movieList?.let { movieList ->
                    if(movieList.isEmpty()) {
                        view.showMovieResultEmpty()
                    }else{
                        view.updateRecyclerView(movieList)
                    }
                }
            }, {
                view.showError(it)
            })
        }
        view.hideKeyBoard()
    }

}