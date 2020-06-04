package com.project.architecturestudy.ui.search

import android.content.Context
import com.project.architecturestudy.data.model.MovieItem

interface SearchContract {

    interface View {

        fun showSearchWordIsEmptyMsg()
        fun showLocalDataSuccessMsg()
        fun showLocalDataFailureMsg()
        fun showRemoteDataSuccessMsg()
        fun showRemoteDataFailureMsg()
        fun showMovieData(item: List<MovieItem>)
        fun showSearchKeyWord(result: String? = null, visibility: Int)
    }

    interface Presenter {

        fun getMovieListFromLocal()
        fun getMovieListFromRemote(context: Context, searchWord: String)
        fun remoteDispose()
        fun invokeTextChanged()
    }
}