package com.jay.architecturestudy.ui.image

import android.os.Bundle
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.databinding.FragmentImageBinding
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.ui.BaseSearchContract
import com.jay.architecturestudy.util.then
import com.jay.architecturestudy.util.toPx
import com.jay.architecturestudy.widget.SpacesItemDecoration

class ImageFragment : BaseFragment<FragmentImageBinding>(R.layout.fragment_image),
    ImageContract.View {
    override val presenter: ImageContract.Presenter by lazy {
        ImagePresenter(this, naverSearchRepository)
    }

    private lateinit var imageAdapter: ImageAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            imageAdapter = ImageAdapter()
            binding.recyclerView.run {
                adapter = imageAdapter
                addItemDecoration(SpacesItemDecoration(12.toPx(), 6.toPx(), 11.toPx()))
            }
        }

        binding.searchBar.onClickAction = { keyword ->
            search(keyword)
        }

        presenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun updateUi(keyword: String, images: List<Image>) {
        keyword.isNotBlank().then {
            binding.searchBar.keyword = keyword


            if (images.isEmpty()) {
                hideResultListView()
                showEmptyResultView()
            } else {
                hideEmptyResultView()
                showResultListView()
                imageAdapter.setData(images)
            }
        }
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
        if (result.isEmpty()) {
            imageAdapter.clear()
        } else {
            imageAdapter.setData(result)
        }
    }
}