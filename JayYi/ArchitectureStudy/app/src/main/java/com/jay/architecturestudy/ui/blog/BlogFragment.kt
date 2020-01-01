package com.jay.architecturestudy.ui.blog

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.util.then
import kotlinx.android.synthetic.main.fragment_blog.*

class BlogFragment : BaseFragment(R.layout.fragment_blog), BlogContract.View {
    override val presenter: BlogContract.Presenter by lazy {
        BlogPresenter(this, naverSearchRepository)
    }

    private lateinit var blogAdapter: BlogAdapter

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

        presenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun updateUi(keyword: String, blogs: List<Blog>) {
        keyword.isNotBlank().then {
            search_bar.keyword = keyword

            if (blogs.isEmpty()) {
                hideResultListView()
                showEmptyResultView()
            } else {
                hideEmptyResultView()
                showResultListView()
                blogAdapter.setData(blogs)
            }

        }
    }

    override fun showEmptyResultView() {
        empty_result_view.visibility = View.VISIBLE
    }

    override fun showResultListView() {
        recycler_view.visibility = View.VISIBLE
    }

    override fun hideEmptyResultView() {
        empty_result_view.visibility = View.GONE
    }

    override fun hideResultListView() {
        recycler_view.visibility = View.GONE
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(blogs: List<Blog>) {
        blogAdapter.setData(blogs)
    }
}