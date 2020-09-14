package com.camai.archtecherstudy.ui.main

import com.camai.archtecherstudy.data.repository.MovieRepositoryImpl


class MainPresenter(
    private val viewMain: MainContract.View,
    private val MovieRepositoryImpl: MovieRepositoryImpl
) : MainContract.Presenter {

    //  Keyword Empty Check
    override fun setSearchKeywordCheck(keyword: String) {
        //  progress View
        viewMain.progressView()

        //   Empty Check
        if (keyword.isNullOrBlank()) {
            //  progress Gone
            viewMain.progressGone()
            //  Toast Null
            viewMain.showEmptyFieldText()
        } else {
            //  Recyclerview Position Init And Call Retrofit
            viewMain.setRecyclerViewScollorPositionInit(keyword)
        }
    }

    //  Retrofit Call
    override fun setSearchMovie(keyword: String) {
        MovieRepositoryImpl.getMovieNameSearch(keyword, 100, 1,
            success = {
                //  Progress Gone
                viewMain.progressGone()
                //  EditText Clear
                viewMain.textClear()

                //  movie list data to recycler View
                viewMain.setDataInsertToAdapter(it)

            },
            failed = {
                //  Progress Gone
                viewMain.progressGone()
                //  EditText Clear
                viewMain.textClear()
                //  Toast Message
                viewMain.showNotFoundMessage(keyword)

            })
    }
}