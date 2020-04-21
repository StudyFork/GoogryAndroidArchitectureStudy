package com.example.studyforkandroid.presenter

import com.example.studyforkandroid.base.BasePresenter
import com.example.studyforkandroid.data.Movie

interface MovieContract {
    interface View {
        fun setItems(list: List<Movie>)
    }

    interface Preseneter : BasePresenter<View> {
        fun loadItem(title: String)
    }
}