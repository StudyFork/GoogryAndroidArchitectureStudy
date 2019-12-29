package com.example.studyapplication.ui.main.blog

import com.example.studyapplication.data.model.SearchBlogResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.network.Conn

class BlogPresenter(val view : BlogContract.View, private val repository: NaverSearchRepository) : BlogContract.Presenter {
    override fun clickSearchButton(query: String) {
        repository.getBlogList(query, object : Conn {
            override fun <T> success(result: T) {
                val searchData : SearchBlogResult? = result as SearchBlogResult
                searchData?.let {
                    view.showList(searchData.arrBlogInfo)
                }
            }

            override fun failed() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}