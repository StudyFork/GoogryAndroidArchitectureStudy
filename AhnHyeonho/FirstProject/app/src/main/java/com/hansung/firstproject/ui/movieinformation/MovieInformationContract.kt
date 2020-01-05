package com.hansung.firstproject.ui.movieinformation

interface MovieInformationContract {

    interface View {
        fun readUrl()

        fun loadWebView()
    }

    interface Presenter {
        fun readUrl()

        fun loadWebView()
    }
}