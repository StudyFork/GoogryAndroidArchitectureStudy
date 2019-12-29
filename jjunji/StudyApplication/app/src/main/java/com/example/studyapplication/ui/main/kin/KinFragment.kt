package com.example.studyapplication.ui.main.kin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyapplication.R
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSourceImpl
import com.example.studyapplication.ui.main.kin.adapter.KinAdapter
import com.example.studyapplication.data.model.SearchKinResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.data.repository.NaverSearchRepositoryImpl
import com.example.studyapplication.ui.base.SearchFragment
import kotlinx.android.synthetic.main.fragment_kin.*

class KinFragment  : SearchFragment(R.layout.fragment_kin), KinContract.View {
    private lateinit var presenter: KinContract.UserActions
    private lateinit var kinAdapter: KinAdapter
    private val repository : NaverSearchRepository = NaverSearchRepositoryImpl(NaverRemoteDataSourceImpl())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = KinPresenter(this, repository)

        btnSearch.setOnClickListener(btnSearchClickListener())
        kinAdapter = KinAdapter()
        recyclerView.adapter = kinAdapter
    }

    // 검색 버튼 클릭 리스너
    private fun btnSearchClickListener() : View.OnClickListener {
        return View.OnClickListener {
            val kinTitle = etQuery.text.toString()
            presenter.clickSearchButton(kinTitle)
        }
    }

    override fun showList(items: Array<SearchKinResult.KinInfo>) {
        kinAdapter.resetItem(items)
    }
}