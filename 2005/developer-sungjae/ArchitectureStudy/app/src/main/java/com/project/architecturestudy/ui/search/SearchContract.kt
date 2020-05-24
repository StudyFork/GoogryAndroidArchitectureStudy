package com.project.architecturestudy.ui.search

interface SearchContract {

    interface View {

        fun showSearchWordIsEmpty(emptyMsg: String)
        fun showLocalDataSuccess(successMsg: String)
        fun showLocalDataFailure(failureMsg: String)
        fun showRemoteDataSuccess(successMsg: String)
        fun showRemoteDataFailure(failureMsg: String)
    }

    interface Presenter {

        fun validateSearchWord(searchWord: String)
        fun getMovieListFromLocal()
        fun getMovieListFromRemote()
    }
}