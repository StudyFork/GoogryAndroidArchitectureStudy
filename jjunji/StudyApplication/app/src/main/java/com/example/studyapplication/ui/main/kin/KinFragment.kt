package com.example.studyapplication.ui.main.kin

import android.os.Bundle
import android.view.View
import com.example.studyapplication.R
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSourceImpl
import com.example.studyapplication.data.model.KinInfo
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.data.repository.NaverSearchRepositoryImpl
import com.example.studyapplication.ui.main.base.BaseSearchFragment
import com.example.studyapplication.ui.main.kin.adapter.KinAdapter
import kotlinx.android.synthetic.main.fragment_kin.*

class KinFragment : BaseSearchFragment(R.layout.fragment_kin), KinContract.View {
    private lateinit var presenter: KinContract.Presenter
    private lateinit var kinAdapter: KinAdapter
    private val repository: NaverSearchRepository =
        NaverSearchRepositoryImpl(NaverRemoteDataSourceImpl())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = KinPresenter(this, repository)

        btnSearch.setOnClickListener(btnSearchClickListener())
        kinAdapter = KinAdapter()
        recyclerView.adapter = kinAdapter
    }

    // 검색 버튼 클릭 리스너
    private fun btnSearchClickListener(): View.OnClickListener {
        return View.OnClickListener {
            val kinTitle = etQuery.text.toString()
            presenter.clickSearchButton(kinTitle)
        }
    }

    override fun showList(items: ArrayList<KinInfo>) {
        kinAdapter.resetItem(items)
    }

}