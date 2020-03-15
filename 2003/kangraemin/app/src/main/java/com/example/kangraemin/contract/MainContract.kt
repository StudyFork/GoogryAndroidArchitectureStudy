package com.example.kangraemin.contract

import com.example.kangraemin.base.KangBasePresenter
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.local.datadao.LocalMovieDataSourceImpl
import com.example.kangraemin.model.remote.datadao.MovieRemoteDataSourceImpl
import com.example.kangraemin.model.remote.datamodel.MovieDetail
import com.example.kangraemin.util.NetworkUtil

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
        fun checkAutoLoginStatus(authRepository: AuthRepository)
        fun deleteAutoLoginStatus(authRepository: AuthRepository)
        fun hasEnteredSearchText(searchText: String)
        fun checkNetworkStatus(networkStatus: NetworkUtil.NetworkStatus)
        fun getMovies(
            remoteMovieDataSource: MovieRemoteDataSourceImpl,
            localMovieDataSource: LocalMovieDataSourceImpl,
            searchText: String
        )
    }
}