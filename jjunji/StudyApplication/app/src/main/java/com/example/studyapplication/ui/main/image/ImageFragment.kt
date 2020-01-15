package com.example.studyapplication.ui.main.image

import android.os.Bundle
import android.view.View
import com.example.studyapplication.R
import com.example.studyapplication.data.datasource.local.NaverLocalDataSourceImpl
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSourceImpl
import com.example.studyapplication.data.model.ImageInfo
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.data.repository.NaverSearchRepositoryImpl
import com.example.studyapplication.ui.main.base.BaseSearchFragment
import com.example.studyapplication.ui.main.image.adapter.ImageAdapter
import kotlinx.android.synthetic.main.fragment_image.*
import kotlinx.android.synthetic.main.fragment_image.btnSearch
import kotlinx.android.synthetic.main.fragment_image.etQuery
import kotlinx.android.synthetic.main.fragment_image.recyclerView
import kotlinx.android.synthetic.main.fragment_image.tvEmpty
import kotlinx.android.synthetic.main.fragment_movie.*

class ImageFragment : BaseSearchFragment(R.layout.fragment_image), ImageContract.View {
    private lateinit var presenter: ImageContract.Presenter
    private lateinit var imageAdapter: ImageAdapter
    private val repository: NaverSearchRepository =
        NaverSearchRepositoryImpl(NaverRemoteDataSourceImpl(), NaverLocalDataSourceImpl())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = ImagePresenter(this, repository)

        btnSearch.setOnClickListener(btnSearchClickListener())
        imageAdapter = ImageAdapter()
        recyclerView.adapter = imageAdapter

        presenter.checkCacheData()
    }

    // 검색 버튼 클릭 리스너
    private fun btnSearchClickListener(): View.OnClickListener {
        return View.OnClickListener {
            val query = etQuery.text.toString()
            presenter.clickSearchButton(query)
        }
    }

    override fun showList(items: ArrayList<ImageInfo>) {
        if (tvEmpty.visibility == View.VISIBLE) {
            tvEmpty.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }

        imageAdapter.resetItem(items)
    }

    override fun showEmptyView() {
        if(recyclerView.visibility == View.VISIBLE) {
            recyclerView.visibility = View.GONE
        }
        tvEmpty.visibility = View.VISIBLE
    }
}