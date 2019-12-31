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
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_page.*


class KinFragment : BaseFragment(R.layout.fragment_page), PageContract.View<KinItem> {

    private lateinit var kinAdapter: KinAdapter
    private lateinit var presenter: KinPresenter
    private lateinit var kinList: List<KinItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
        setUpBuuttonClickListener()
    }

    private fun start() {
        presenter = KinPresenter(this)
        button_home_search.text = "블로그 검색"
        kinAdapter = KinAdapter(this::onKinItemClick)
        with(recyclerview_home) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = kinAdapter
            checkHistoryItems()
        }
    }

    private fun checkHistoryItems() {
        if (Hawk.get("kinList", null) != null) {
            kinList = Hawk.get("kinList")
            kinAdapter.resetItems(kinList)
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

    override fun getItems(items: List<KinItem>) {
        if (items.size == 0) {
            setEmptyView(true)
            Hawk.put("kinList", null)
            kinAdapter.removeAll()
        } else {
            setEmptyView(false)
            kinList = items
            kinAdapter.resetItems(items)
            Hawk.put("kinList", kinList)
        }
    }

    override fun getError(message: String) = toast(message)

}
