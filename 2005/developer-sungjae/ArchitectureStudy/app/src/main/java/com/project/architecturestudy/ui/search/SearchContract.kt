package com.project.architecturestudy.ui.search

import com.project.architecturestudy.data.model.MovieItem

interface SearchContract {

    interface View {

        fun showSearchWordIsEmpty()
        fun showLocalDataSuccess()
        fun showLocalDataFailure()
        fun showRemoteDataSuccess()
        fun showRemoteDataFailure()
        fun setLocalMovieData(item: ArrayList<MovieItem>)
        fun setRemoteMovieData(item: List<MovieItem>)
    }

    interface Presenter {

        fun validateSearchWord(searchWord: String)
        fun getMovieListFromLocal()
        fun getMovieListFromRemote(searchWord: String)
        fun remoteDispose()
    }
}