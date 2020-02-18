package com.example.studyapplication.ui.main.kin

import android.os.Bundle
import android.view.View
import com.example.studyapplication.R
import com.example.studyapplication.data.datasource.local.NaverLocalDataSourceImpl
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSourceImpl
import com.example.studyapplication.data.model.KinInfo
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.data.repository.NaverSearchRepositoryImpl
import com.example.studyapplication.ui.main.base.BaseSearchFragment
import com.example.studyapplication.ui.main.kin.adapter.KinAdapter
import kotlinx.android.synthetic.main.fragment_kin.*
import kotlinx.android.synthetic.main.fragment_kin.btnSearch
import kotlinx.android.synthetic.main.fragment_kin.etQuery
import kotlinx.android.synthetic.main.fragment_kin.recyclerView
import kotlinx.android.synthetic.main.fragment_kin.tvEmpty
import kotlinx.android.synthetic.main.fragment_movie.*

class KinFragment : BaseSearchFragment(R.layout.fragment_kin), KinContract.View {
    private lateinit var presenter: KinContract.Presenter
    private lateinit var kinAdapter: KinAdapter
    private val repository: NaverSearchRepository =
        NaverSearchRepositoryImpl(NaverRemoteDataSourceImpl(), NaverLocalDataSourceImpl())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = KinPresenter(this, repository)

        btnSearch.setOnClickListener(btnSearchClickListener())
        kinAdapter = KinAdapter()
        recyclerView.adapter = kinAdapter

        presenter.checkCacheData()
    }

    // 검색 버튼 클릭 리스너
    private fun btnSearchClickListener(): View.OnClickListener {
        return View.OnClickListener {
            val kinTitle = etQuery.text.toString()
            presenter.clickSearchButton(kinTitle)
        }
    }

    override fun showList(items: ArrayList<KinInfo>) {
        if (tvEmpty.visibility == View.VISIBLE) {
            tvEmpty.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
        kinAdapter.resetItem(items)
    }

    override fun showEmptyView() {
        if(recyclerView.visibility == View.VISIBLE) {
            recyclerView.visibility = View.GONE
        }
        tvEmpty.visibility = View.VISIBLE
    }

}