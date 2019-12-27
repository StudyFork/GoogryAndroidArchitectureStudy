package com.example.studyapplication.main.image

import com.example.studyapplication.data.model.SearchImageResult

interface ImageContract {
    interface View {
        fun showList(items: Array<SearchImageResult.ImageInfo>)
    }

    interface UserActions {
        fun clickSearchButton(query : String)
    }

}