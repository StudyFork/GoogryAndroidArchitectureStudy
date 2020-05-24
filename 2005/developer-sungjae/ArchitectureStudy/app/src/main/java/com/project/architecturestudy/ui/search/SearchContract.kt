package com.project.architecturestudy.ui.search

import android.content.Intent

interface SearchContract {

    interface View {

        fun moveWebMovieDetailPage(intent: Intent)
        fun showSearchWordIsEmpty()
        fun showLocalDataSuccess()
        fun showLocalDataFailure()
        fun showRemoteDataSuccess()
        fun showRemoteDataFailure()
    }

    interface Presenter {

        val adapter: SearchAdapter
        fun validateSearchWord(searchWord: String)
        fun getMovieListFromLocal()
        fun getMovieListFromRemote(searchWord: String)
        fun setItemClickListener()
        fun remoteDispose()
    }
}