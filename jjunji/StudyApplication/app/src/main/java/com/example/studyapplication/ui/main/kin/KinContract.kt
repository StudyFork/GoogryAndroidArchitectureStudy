package com.example.studyapplication.ui.main.kin

import com.example.studyapplication.data.model.KinInfo
import com.example.studyapplication.ui.main.base.BaseSearchContract

interface KinContract {
    interface View : BaseSearchContract.View {
        fun showList(items: ArrayList<KinInfo>)
    }

    interface Presenter : BaseSearchContract.Presenter {
        fun clickSearchButton(query: String)
        fun checkCacheData()
    }
}