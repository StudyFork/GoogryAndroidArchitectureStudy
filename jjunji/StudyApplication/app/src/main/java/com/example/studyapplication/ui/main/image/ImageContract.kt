package com.example.studyapplication.ui.main.image

import com.example.studyapplication.data.model.SearchImageResult

interface ImageContract {
    interface View {
        fun showList(items: Array<SearchImageResult.ImageInfo>)
        fun toastErrorConnFailed(message : String)
    }

    interface Presenter {
        fun clickSearchButton(query : String)
    }

}