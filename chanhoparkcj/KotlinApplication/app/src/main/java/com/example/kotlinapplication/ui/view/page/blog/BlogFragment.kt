package com.example.kotlinapplication.ui.view.page.blog

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.BlogItem
import com.example.kotlinapplication.ui.base.BaseFragment
import com.example.kotlinapplication.ui.view.home.presenter.BlogPresenter
import com.example.kotlinapplication.ui.view.page.PageContract
import kotlinx.android.synthetic.main.fragment_page.*


class BlogFragment : BaseFragment(R.layout.fragment_page), ListBlogAdapter.ItemListener, PageContract.View<BlogItem> {
    private lateinit var blogAdapter: ListBlogAdapter
    private lateinit var presenter: BlogPresenter
    private lateinit var blogList: List<BlogItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
        setUpBuuttonClickListener()
    }

    private fun start() {
        presenter = BlogPresenter(this)
        home_search_btn.text = "블로그 검색"
        blogAdapter = ListBlogAdapter(this)
        with(home_recyclerview) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = blogAdapter
        }
    }

    private fun setUpBuuttonClickListener() {
        home_search_btn.setOnClickListener {
            if (home_search_edit.text.isBlank()) {
                toast("검색어를 입력하세요")
            } else {
                toast("검색어 :${home_search_edit.text}")
                presenter.loadData(home_search_edit.text.toString())
            }
        }
    }

    override fun onBlogItemClick(blogItems: BlogItem) {
        toast(blogItems.link)
        webLink(blogItems.link)
    }
    override fun getItems(items: List<BlogItem>) {
        blogList = items
        blogAdapter.addAllItems(items)
    }

    override fun getError(message: String) {
        toast(message)
    }
}
