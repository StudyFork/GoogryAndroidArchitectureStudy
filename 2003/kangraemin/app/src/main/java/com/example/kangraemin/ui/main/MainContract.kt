package com.example.kangraemin.ui.main

import com.example.kangraemin.base.KangBasePresenter
import com.example.kangraemin.model.remote.datamodel.MovieDetail

interface MainContract {
    interface View {
        fun showLogOutButton()
        fun enableSearchButton()
        fun disableSearchButton()
        fun startLoginActivity()
        fun showNetworkErrorText()
        fun hideNetworkErrorText()
        fun setMoviesInAdapter(movies: ArrayList<MovieDetail>)
    }

    interface Presenter : KangBasePresenter {
        fun checkAutoLoginStatus()
        fun deleteAutoLoginStatus()
        fun hasEnteredSearchText(searchText: String)
        fun checkNetworkStatus()
        fun getMovies(searchText: String)
    }
}