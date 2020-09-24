package com.camai.archtecherstudy.ui.main

import com.camai.archtecherstudy.data.model.Items

interface MainContract {

    interface View{
        fun progressViewStatus(status: Boolean)
        fun textClear()
        fun setRecyclerViewScollorPositionInit(keyword: String)
        fun setDataInsertToAdapter(data: ArrayList<Items>)
        fun showEmptyFieldText()
        fun showNotFoundMessage(keyword: String)
        fun openWebView(url: String)
    }

    interface Presenter{
        fun openWeb(url: String)
        fun setSearchKeywordCheck(keyword: String)
        fun setSearchMovie(keyword: String)
    }
}