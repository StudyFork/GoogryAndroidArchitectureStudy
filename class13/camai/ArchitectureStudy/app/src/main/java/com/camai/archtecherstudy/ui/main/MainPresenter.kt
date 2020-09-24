package com.camai.archtecherstudy.ui.main

import com.camai.archtecherstudy.data.repository.MovieRepository


class MainPresenter(
    private val viewMain: MainContract.View,
    private val movieRepositoryImpl: MovieRepository
) : MainContract.Presenter {



    override fun openWeb(url: String) {
        viewMain.openWebView(url)
    }

    //  Keyword Empty Check
    override fun setSearchKeywordCheck(keyword: String) {
        //  progress View
        viewMain.progressViewStatus(true)

        //   Empty Check
        if (keyword.isNullOrBlank()) {
            //  progress Gone
            viewMain.progressViewStatus(false)
            //  Toast Null
            viewMain.showEmptyFieldText()
        } else {
            //  Recyclerview Position Init And Call Retrofit
            viewMain.setRecyclerViewScollorPositionInit(keyword)
        }
    }

    //  Retrofit Call
    override fun setSearchMovie(keyword: String) {
        movieRepositoryImpl.getMovieNameSearch(keyword, 100, 1,
            success = {
                //  Progress Gone
                viewMain.progressViewStatus(false)
                //  EditText Clear
                viewMain.textClear()

                //  movie list data to recycler View
                viewMain.setDataInsertToAdapter(it)

            },
            failed = {
                //  Progress Gone
                viewMain.progressViewStatus(false)
                //  EditText Clear
                viewMain.textClear()
                //  Toast Message
                viewMain.showNotFoundMessage(keyword)

            })
    }
}