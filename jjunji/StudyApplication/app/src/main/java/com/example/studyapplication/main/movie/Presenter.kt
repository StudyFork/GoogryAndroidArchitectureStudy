package com.example.studyapplication.main.movie

import com.example.studyapplication.data.model.SearchMovieResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.network.Conn

class Presenter (val view: Contract.View, val repository: NaverSearchRepository) : Contract.UserActions {
    override fun clickSearchButton(query: String) {
        repository.getMovieList(query, object : Conn {
            override fun <T> success(result: T) {
                val searchData : SearchMovieResult? = result as SearchMovieResult
                searchData?.let {
                    view.showList(searchData.arrMovieInfo)
                }
            }

            override fun failed() {
                TODO()
            }
        })
    }

}