package com.example.studyapplication.ui.main.image

import android.os.Bundle
import android.view.View
import com.example.studyapplication.R
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSourceImpl
import com.example.studyapplication.ui.main.image.adapter.ImageAdapter
import com.example.studyapplication.data.model.SearchImageResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.data.repository.NaverSearchRepositoryImpl
import com.example.studyapplication.ui.base.SearchFragment
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : SearchFragment(R.layout.fragment_image), ImageContract.View {
    private lateinit var presenter: ImageContract.Presenter
    private lateinit var imageAdapter: ImageAdapter
    private val repository : NaverSearchRepository = NaverSearchRepositoryImpl(NaverRemoteDataSourceImpl())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = ImagePresenter(this, repository)

        btnSearch.setOnClickListener(btnSearchClickListener())
        imageAdapter = ImageAdapter()
        recyclerView.adapter = imageAdapter
    }

    // 검색 버튼 클릭 리스너
    private fun btnSearchClickListener() : View.OnClickListener {
        return View.OnClickListener {
            val query = etQuery.text.toString()
            presenter.clickSearchButton(query)
        }
    }

    override fun showList(items: Array<SearchImageResult.ImageInfo>) {
        imageAdapter.resetItem(items)
    }

}