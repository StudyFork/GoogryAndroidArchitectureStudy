package com.hansung.firstproject.ui.movieinformation

class MovieInformationPresenter(
    private val view: MovieInformationContract.View
) : MovieInformationContract.Presenter {
    override fun readUrl() {
        view.readUrl()
    }

    override fun loadWebView() {
        view.loadWebView()
    }
}