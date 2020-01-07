package com.example.kotlinapplication.ui.view.page.blog

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.BlogItem
import com.example.kotlinapplication.ui.base.BaseBindingFragment
import com.example.kotlinapplication.ui.view.home.presenter.BlogPresenter
import com.example.kotlinapplication.ui.view.page.PageContract


class BlogFragment : BaseBindingFragment(R.layout.fragment_page), PageContract.View<BlogItem> {

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
        binding.buttonHomeSearch.text = "블로그 검색"
        blogAdapter = BlogAdapter(this::onBlogItemClick)
        with(binding.recyclerviewHome) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = blogAdapter
            checkHistoryItems()
        }
    }

    private fun checkHistoryItems() {
        if (presenter.getLocalItems().isNotEmpty()) {
            blogList = presenter.getLocalItems()
            blogAdapter.resetItems(blogList)
            setEmptyView(false)
        } else {
            setEmptyView(true)
        }
    }

    private fun setUpBuuttonClickListener() {
        binding.buttonHomeSearch.setOnClickListener {
            if (binding.edittextHomeSearchedit.text.isBlank()) {
                toast("검색어를 입력하세요")
            } else {
                toast("검색어 :${binding.edittextHomeSearchedit.text}")
                presenter.loadData(binding.edittextHomeSearchedit.text.toString())
            }
        }
    }

    private fun onBlogItemClick(blogItems: BlogItem) {
        toast(blogItems.link)
        webLink(blogItems.link)
    }

    override fun getItems(items: List<BlogItem>) {
        if (items.size == 0) {
            setEmptyView(true)
            blogAdapter.removeAll()
        } else {
            setEmptyView(false)
            blogList = items
            blogAdapter.resetItems(items)
        }
    }

    override fun getError(message: String) = toast(message)


}
