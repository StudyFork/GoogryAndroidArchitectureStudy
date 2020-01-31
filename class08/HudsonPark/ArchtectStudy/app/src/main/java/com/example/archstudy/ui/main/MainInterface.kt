package com.example.archstudy.ui.main

import com.example.archstudy.base.BasePresenter
import com.example.archstudy.base.BaseView
import com.example.archstudy.data.source.local.MovieData

interface MainInterface {

    interface View  : BaseView{
        fun initPresenter()
        fun showDataList(dataList : MutableList<MovieData>)
    }

    interface Presenter : BasePresenter<View>{
        fun getRemoteDataByQuery(query : String?)
        fun getLocalQuery() : String?
        fun getLocalData(query: String?)
    }
}