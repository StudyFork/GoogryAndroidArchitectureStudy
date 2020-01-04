package com.example.studyapplication.ui.main.image

import com.example.studyapplication.data.model.ImageInfo
import com.example.studyapplication.data.model.SearchResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.network.Conn
import com.example.studyapplication.ui.main.base.BaseSearchPresenter

class ImagePresenter(
    override val view: ImageContract.View,
    private val repository: NaverSearchRepository
) : ImageContract.Presenter,
    BaseSearchPresenter(view) {

    override fun clickSearchButton(query: String) {
        repository.getImageList(query, object : Conn {
            override fun <T> success(result: T) {
                val searchData: SearchResult<ImageInfo> = result as SearchResult<ImageInfo>
                searchData.let { view.showList(searchData.arrItem) }
            }

            override fun failed(e: Throwable) {
                onRequestFailed(e)
            }

        })
    }

}