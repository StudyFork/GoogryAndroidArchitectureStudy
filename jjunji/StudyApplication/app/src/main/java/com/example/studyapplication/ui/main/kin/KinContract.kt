package com.example.studyapplication.ui.main.kin

import com.example.studyapplication.data.model.SearchKinResult

interface KinContract {
    interface View {
        fun showList(items: Array<SearchKinResult.KinInfo>)
    }

    interface Presenter {
        fun clickSearchButton(query : String)
    }
}