package com.example.studyapplication.ui.main.kin

import com.example.studyapplication.data.model.KinInfo

interface KinContract {
    interface View {
        fun showList(items: ArrayList<KinInfo>)
        fun toastErrorConnFailed(message: String)
    }

    interface Presenter {
        fun clickSearchButton(query: String)
    }
}