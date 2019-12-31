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
        Hawk.init(context).build()
        start()
        setUpBuuttonClickListener()
    }

    private fun start() {
        presenter = ImagePresenter(this)
        home_search_btn.text = "블로그 검색"
        ImageAdapter = ImageAdapter(this::onImageItemClick)
        with(home_recyclerview) {
            layoutManager = GridLayoutManager(activity,3)
            adapter = ImageAdapter
            checkHistoryItems()
        }
    }
    private fun checkHistoryItems(){
        if(Hawk.get("imageList",null)!=null){
            imageList = Hawk.get("imageList")
            ImageAdapter.resetItems(imageList)
        }
    }

    private fun setUpBuuttonClickListener() = home_search_btn.setOnClickListener {
        if (home_search_edit.text.isBlank()) {
            toast("검색어를 입력하세요")
        } else {
            toast("검색어 :${home_search_edit.text}")
            presenter.loadData(home_search_edit.text.toString())
        }
    }

    override fun getItems(items: List<ImageItem>) {
        imageList = items
        Hawk.put("imageList",imageList)
        ImageAdapter.resetItems(items)
    }

    private fun onImageItemClick(imageItems: ImageItem) {
        toast(imageItems.link)
        webLink(imageItems.link)
    }

    override fun getError(message: String) = toast(message)

}
