package com.example.androidarchitecturestudy.presenter

import com.example.androidarchitecturestudy.data.repository.MovieRepository
import com.example.androidarchitecturestudy.room.SearchedDataBase

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
                    if (movieList.isEmpty()) {
                        view.showMovieResultEmpty()
                    } else {
                        view.updateRecyclerView(movieList)
                    }
                }
            }, {
                view.showError(it)
            })
        }
        view.hideKeyBoard()
    }


    override fun saveSearchQuery(searchQuery: String, database: SearchedDataBase) {
        //검색 쿼리  안비어 있을때 진행
        if (searchQuery.isNotEmpty()) {
            repository.saveRecentSearch(searchQuery, database)
        }
    }

}