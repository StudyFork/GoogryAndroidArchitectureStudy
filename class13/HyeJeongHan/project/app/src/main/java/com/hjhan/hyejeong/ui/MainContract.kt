package com.hjhan.hyejeong.ui

import com.hjhan.hyejeong.data.model.Item
import com.hjhan.hyejeong.ui.base.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun emptyQuery()
        fun emptyMovieList()
        fun setMovieList(list: List<Item>)
    }

    interface Presenter : BaseContract.Presenter {
        fun getMovieList(query: String)
    }

}