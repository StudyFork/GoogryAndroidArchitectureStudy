package com.example.studyapplication.ui.main.movie

import com.example.studyapplication.data.model.MovieInfo
import com.example.studyapplication.data.model.SearchResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.network.Conn

class MoviePresenter(val view: MovieContract.View, private val repository: NaverSearchRepository) :
    MovieContract.Presenter {
    override fun clickSearchButton(query: String) {
        repository.getMovieList(query, object : Conn {
            override fun <T> success(result: T) {
                val searchData: SearchResult<MovieInfo> = result as SearchResult<MovieInfo>
                searchData.let {
                    view.showList(searchData.arrItem)
                }
            }

            override fun failed(e: Throwable) {

            }
        })
    }

}