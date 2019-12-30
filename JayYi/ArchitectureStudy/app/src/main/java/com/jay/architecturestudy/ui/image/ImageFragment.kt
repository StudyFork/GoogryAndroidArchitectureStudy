package com.jay.architecturestudy.ui.image

import android.os.Bundle
import android.view.View
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.util.toPx
import com.jay.architecturestudy.widget.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : BaseFragment(R.layout.fragment_image), ImageContract.View {
    override val presenter: ImageContract.Presenter by lazy {
        ImagePresenter(this, naverSearchRepository)
    }

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showEmptyResultView() {
        empty_result_view.visibility = View.VISIBLE
    }

    override fun showResultListView() {
        recycler_view.visibility = View.VISIBLE
    }

    override fun hideEmptyResultView() {
        empty_result_view.visibility = View.GONE
    }

    override fun hideResultListView() {
        recycler_view.visibility = View.GONE
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Image>) {
        imageAdapter.setData(result)
    }
}