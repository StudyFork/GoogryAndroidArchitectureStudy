package com.example.studyapplication.main.kin

import com.example.studyapplication.data.model.SearchKinResult

interface KinContract {
    interface View {
        fun showList(items: Array<SearchKinResult.KinInfo>)
    }

    interface UserActions {
        fun clickSearchButton(query : String)
    }
}