package com.example.study.ui.detail

interface DetailContract {

    interface View {
        fun showDetailView(movieUrl: String)
    }

    interface Presenter {}
}
