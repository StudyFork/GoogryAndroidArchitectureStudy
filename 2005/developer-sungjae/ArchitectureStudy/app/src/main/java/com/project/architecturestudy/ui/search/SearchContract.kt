package com.project.architecturestudy.ui.search

import com.project.architecturestudy.data.model.MovieItem

interface SearchContract {

    interface View {

        fun showSearchWordIsEmptyMsg()
        fun showLocalDataSuccessMsg()
        fun showLocalDataFailureMsg()
        fun showRemoteDataSuccessMsg()
        fun showRemoteDataFailureMsg()
        fun showLocalMovieData(item: ArrayList<MovieItem>)
        fun showRemoteMovieData(item: List<MovieItem>)
    }

    interface Presenter {

        fun validateSearchWord(searchWord: String)
        fun getMovieListFromLocal()
        fun getMovieListFromRemote(searchWord: String)
        fun remoteDispose()
    }
}