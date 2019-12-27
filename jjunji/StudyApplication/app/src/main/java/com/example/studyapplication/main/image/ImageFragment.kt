package com.example.studyapplication.main.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyapplication.R
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSourceImpl
import com.example.studyapplication.main.image.adapter.ImageAdapter
import com.example.studyapplication.data.model.SearchImageResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.data.repository.NaverSearchRepositoryImpl
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : Fragment(), ImageContract.View {
    private lateinit var presenter: ImageContract.UserActions
    private lateinit var imageAdapter: ImageAdapter
    private val repository : NaverSearchRepository = NaverSearchRepositoryImpl(NaverRemoteDataSourceImpl())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

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

    companion object {
        fun newInstance() : ImageFragment {
            return ImageFragment()
        }
    }
}