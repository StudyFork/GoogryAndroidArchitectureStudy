package com.jay.architecturestudy.ui.blog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.util.showToastMessage
import kotlinx.android.synthetic.main.fragemnt_blog.*
import kotlinx.android.synthetic.main.fragemnt_movie.*
import kotlinx.android.synthetic.main.fragemnt_movie.recycler_view
import kotlinx.android.synthetic.main.fragemnt_movie.search_bar

class BlogFragment : BaseFragment(R.layout.fragemnt_blog), BlogContract.View {
    override lateinit var presenter: BlogContract.Presenter

    private lateinit var blogAdapter: BlogAdapter

    private val naverSearchRepository by lazy {
        NaverSearchRepositoryImpl()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { activity ->
            blogAdapter = BlogAdapter()
            recycler_view.run {
                adapter = blogAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        activity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }

        search_bar.onClickAction = { keyword ->
            search(keyword)
        }

        presenter = BlogPresenter(this, naverSearchRepository)
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(blogs: List<Blog>) {
        blogAdapter.setData(blogs)
    }

    override fun showErrorMessage(message: String) {
        context?.showToastMessage(message)
    }
}