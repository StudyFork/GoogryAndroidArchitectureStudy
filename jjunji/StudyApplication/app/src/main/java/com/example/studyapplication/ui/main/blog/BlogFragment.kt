package com.example.studyapplication.ui.main.blog

import android.os.Bundle
import android.view.View
import com.example.studyapplication.R
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSourceImpl
import com.example.studyapplication.data.model.SearchBlogResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.data.repository.NaverSearchRepositoryImpl
import com.example.studyapplication.ui.base.SearchFragment
import com.example.studyapplication.ui.main.blog.adapter.BlogAdapter
import kotlinx.android.synthetic.main.fragment_blog.*

class BlogFragment : SearchFragment(R.layout.fragment_blog), BlogContract.View {
    private val repository: NaverSearchRepository = NaverSearchRepositoryImpl(NaverRemoteDataSourceImpl())
    private lateinit var blogAdapter: BlogAdapter
    private lateinit var presenter : BlogContract.Presenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = BlogPresenter(this, repository)

        btnSearch.setOnClickListener(btnSearchClickListener())
        blogAdapter = BlogAdapter()
        recyclerView.adapter = blogAdapter
    }

    // 검색 버튼 클릭 리스너
    private fun btnSearchClickListener() : View.OnClickListener {
        return View.OnClickListener {
            val blogTitle = etQuery.text.toString()
            presenter.clickSearchButton(blogTitle)
        }
    }

    override fun showList(items: Array<SearchBlogResult.BlogInfo>) {
        blogAdapter.resetItem(items)
    }

}