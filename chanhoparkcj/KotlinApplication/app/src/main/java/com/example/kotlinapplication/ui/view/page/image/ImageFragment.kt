package com.example.kotlinapplication.ui.view.page.image

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.ImageItem
import com.example.kotlinapplication.ui.base.BaseFragment
import com.example.kotlinapplication.ui.view.home.presenter.ImagePresenter
import com.example.kotlinapplication.ui.view.page.PageContract
import kotlinx.android.synthetic.main.fragment_page.*


class ImageFragment : BaseFragment(R.layout.fragment_page), ImageListAdapter.ItemListener, PageContract.View<ImageItem> {
    private lateinit var ImageAdapter: ImageListAdapter
    private lateinit var presenter: ImagePresenter
    private lateinit var ImageList: List<ImageItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
        setUpBuuttonClickListener()
    }

    private fun start() {
        presenter = ImagePresenter(this)
        home_search_btn.text = "블로그 검색"
        ImageAdapter = ImageListAdapter(this)
        with(home_recyclerview) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = ImageAdapter
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
    override fun getItems(items: List<ImageItem>) {
        ImageList = items
        ImageAdapter.addAllItems(items)
    }

    override fun onImageItemClick(imageItems: ImageItem) {
        toast(imageItems.link)
        webLink(imageItems.link)
    }

    override fun getError(message: String) {
        toast(message)
    }
}
