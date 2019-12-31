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
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_page.*


class BlogFragment : BaseFragment(R.layout.fragment_page), PageContract.View<BlogItem> {
    private lateinit var blogAdapter: BlogAdapter
    private lateinit var presenter: BlogPresenter
    private lateinit var blogList: List<BlogItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
        setUpBuuttonClickListener()
    }

    private fun start() {
        presenter = BlogPresenter(this)
        button_home_search.text = "블로그 검색"
        blogAdapter = BlogAdapter(this::onBlogItemClick)
        with(recyclerview_home) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = blogAdapter
            checkHistoryItems()
        }
    }

    private fun checkHistoryItems() {
        if (Hawk.get("blogList", null) != null) {
            blogList = Hawk.get("blogList")
            blogAdapter.resetItems(blogList)
            setEmptyView(false)
        } else {
            setEmptyView(true)
        }
    }

    private fun setUpBuuttonClickListener() {
        button_home_search.setOnClickListener {
            if (edittext_home_searchedit.text.isBlank()) {
                toast("검색어를 입력하세요")
            } else {
                toast("검색어 :${edittext_home_searchedit.text}")
                presenter.loadData(edittext_home_searchedit.text.toString())
            }
        }
    }

    override fun getItems(items: List<BlogItem>) {
        if (items.size == 0) {
            setEmptyView(true)
            Hawk.put("blogList", null)
            blogAdapter.removeAll()
        } else {
            setEmptyView(false)
            blogList = items
            blogAdapter.resetItems(items)
            Hawk.put("blogList", blogList)
        }
    }

    override fun getError(message: String) = toast(message)
}
