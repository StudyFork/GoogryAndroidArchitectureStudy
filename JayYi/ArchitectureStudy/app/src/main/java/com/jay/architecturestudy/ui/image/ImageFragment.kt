package com.jay.architecturestudy.ui.image

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.data.model.ResponseNaverQuery
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.network.Api
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.util.toPx
import com.jay.architecturestudy.widget.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragemnt_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageFragment : BaseFragment(R.layout.fragemnt_image), ImageContract.View {
    override lateinit var presenter: ImageContract.Presenter

    private lateinit var imageAdapter: ImageAdapter

    private val naverSearchRepository by lazy {
        NaverSearchRepositoryImpl()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            imageAdapter = ImageAdapter()
            recycler_view.run {
                adapter = imageAdapter
                addItemDecoration(SpacesItemDecoration(12.toPx(), 6.toPx(), 11.toPx()))
            }
        }

        search_bar.onClickAction = { keyword ->
            search(keyword)
        }

        presenter = ImagePresenter(this, naverSearchRepository)
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Image>) {
        imageAdapter.setData(result)
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}