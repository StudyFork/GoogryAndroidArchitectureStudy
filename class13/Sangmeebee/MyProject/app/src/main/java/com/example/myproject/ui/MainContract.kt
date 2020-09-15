package com.example.myproject.ui

import com.example.myproject.data.model.Items
import com.example.myproject.ui.base.BaseContract

interface MainContract {
    interface View : BaseContract.View {
        fun showQueryEmpty()
        fun initScroll()
        fun showResultEmpty()
        fun showResult(items: ArrayList<Items>)
    }

    interface Presenter: BaseContract.Presenter {
        fun searchMovieList(title: String)
    }
}
