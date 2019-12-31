package com.example.kotlinapplication.ui.view.page.image

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.ImageItem
import com.example.kotlinapplication.ui.base.BaseFragment
import com.example.kotlinapplication.ui.view.home.presenter.ImagePresenter
import com.example.kotlinapplication.ui.view.page.PageContract
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_page.*


class ImageFragment : BaseFragment(R.layout.fragment_page), PageContract.View<ImageItem> {
    private lateinit var ImageAdapter: ImageAdapter
    private lateinit var presenter: ImagePresenter
    private lateinit var imageList: List<ImageItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
        setUpBuuttonClickListener()
    }

    private fun start() {
        presenter = ImagePresenter(this)
        button_home_search.text = "블로그 검색"
        ImageAdapter = ImageAdapter(this::onImageItemClick)
        with(recyclerview_home) {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = ImageAdapter
            checkHistoryItems()
        }
    }

    private fun checkHistoryItems() {
        if (Hawk.get("imageList", null) != null) {
            imageList = Hawk.get("imageList")
            ImageAdapter.resetItems(imageList)
            setEmptyView(false)
        } else {
            setEmptyView(true)
        }
    }

    private fun setUpBuuttonClickListener() = button_home_search.setOnClickListener {
        if (edittext_home_searchedit.text.isBlank()) {
            toast("검색어를 입력하세요")
        } else {
            toast("검색어 :${edittext_home_searchedit.text}")
            presenter.loadData(edittext_home_searchedit.text.toString())
        }
    }

    override fun getItems(items: List<ImageItem>) {
        if (items.size == 0) {
            setEmptyView(true)
            Hawk.put("imageList", null)
            ImageAdapter.removeAll()
        } else {
            setEmptyView(false)
            imageList = items
            ImageAdapter.resetItems(items)
            Hawk.put("imageList", imageList)
        }
    }


    override fun getError(message: String) = toast(message)

}
