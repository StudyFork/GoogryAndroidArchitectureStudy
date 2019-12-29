package com.example.studyapplication.ui.main.image

import com.example.studyapplication.data.model.SearchImageResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.network.Conn

class ImagePresenter (val view : ImageContract.View, private val repository: NaverSearchRepository) : ImageContract.Presenter {
    override fun clickSearchButton(query: String) {
        repository.getImageList(query, object : Conn {
            override fun <T> success(result: T) {
                val searchData : SearchImageResult? = result as SearchImageResult
                searchData?.let { view.showList(searchData.arrImageInfo) }
            }

            override fun failed() {

            }
        })
    }

}