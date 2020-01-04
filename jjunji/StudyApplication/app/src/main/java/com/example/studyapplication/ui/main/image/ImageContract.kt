package com.example.studyapplication.ui.main.image

import com.example.studyapplication.data.model.ImageInfo

interface ImageContract {
    interface View {
        fun showList(items: ArrayList<ImageInfo>)
        fun toastErrorConnFailed(message: String)
    }

    interface Presenter {
        fun clickSearchButton(query: String)
    }

}