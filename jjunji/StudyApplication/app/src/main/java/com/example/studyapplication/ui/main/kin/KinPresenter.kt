package com.example.studyapplication.ui.main.kin

import com.example.studyapplication.data.model.KinInfo
import com.example.studyapplication.data.model.SearchResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.network.Conn
import com.example.studyapplication.ui.main.base.BaseSearchPresenter

class KinPresenter(
    override val view: KinContract.View,
    private val repository: NaverSearchRepository
) : KinContract.Presenter,
    BaseSearchPresenter(view) {

    override fun clickSearchButton(query: String) {
        repository.getKinList(query, object : Conn {
            override fun <T> success(result: T) {
                val searchData: SearchResult<KinInfo> = result as SearchResult<KinInfo>
                searchData.let { view.showList(searchData.arrItem) }
            }

            override fun failed(e: Throwable) {
                onRequestFailed(e)
            }
        })
    }

}