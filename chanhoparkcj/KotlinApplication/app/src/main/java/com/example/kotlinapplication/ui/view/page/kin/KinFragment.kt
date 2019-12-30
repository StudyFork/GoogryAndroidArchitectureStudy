package com.example.kotlinapplication.ui.view.page.kin

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.KinItem
import com.example.kotlinapplication.ui.base.BaseFragment
import com.example.kotlinapplication.ui.view.home.presenter.KinPresenter
import com.example.kotlinapplication.ui.view.page.PageContract
import kotlinx.android.synthetic.main.fragment_page.*


class KinFragment : BaseFragment(R.layout.fragment_page), KinListAdapter.ItemListener, PageContract.View<KinItem> {


    private lateinit var kinAdapter: KinListAdapter
    private lateinit var presenter: KinPresenter
    private lateinit var kinList: List<KinItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
        setUpBuuttonClickListener()
    }

    private fun start() {
        presenter = KinPresenter(this)
        home_search_btn.text = "블로그 검색"
        kinAdapter = KinListAdapter(this)
        with(home_recyclerview) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = kinAdapter
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
    override fun getItems(items: List<KinItem>) {
        kinList = items
        kinAdapter.addAllItems(items)
    }
    override fun onKinItemClick(kinItems: KinItem) {
        toast(kinItems.link)
        webLink(kinItems.link)
    }
    override fun getError(message: String) {
        toast(message)
    }
}
