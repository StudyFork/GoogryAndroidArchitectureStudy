package com.example.archstudy.ui.main

import com.example.archstudy.base.BasePresenter
import com.example.archstudy.base.BaseView
import com.example.archstudy.data.source.local.MovieData

interface MainInterface {

    interface View : BaseView {
        fun initPresenter()
        fun showDataList(dataList: MutableList<MovieData>)
    }

    interface Presenter : BasePresenter<View> {
        fun initData()
        fun getData(query: String?)
        fun getRemoteDataByQuery(query: String?)
        fun getQuery(successCallback: (String) -> Unit)
        fun getLocalData(
            query: String?,
            successCallback: (MutableList<MovieData>) -> Unit,
            failCallback: (String) -> Unit
        )

        fun insertData(query: String, data: MutableList<MovieData>)
    }
}