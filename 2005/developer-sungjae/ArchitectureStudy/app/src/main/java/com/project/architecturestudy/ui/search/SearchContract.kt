package com.project.architecturestudy.ui.search

interface SearchContract {

    interface View {

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
        fun remoteDispose()

    }
}