package com.example.studyapplication.ui.main.image

import com.example.studyapplication.data.model.ImageInfo
import com.example.studyapplication.ui.main.base.BaseSearchContract

interface ImageContract {
    interface View : BaseSearchContract.View {
        fun showList(items: ArrayList<ImageInfo>)
    }

    interface Presenter : BaseSearchContract.Presenter {
        fun clickSearchButton(query: String)
    }
}