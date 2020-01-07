package com.jay.architecturestudy.ui.image

import android.os.Bundle
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.databinding.FragmentImageBinding
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.ui.BaseSearchContract
import com.jay.architecturestudy.util.then

class ImageFragment : BaseFragment<FragmentImageBinding>(R.layout.fragment_image),
    ImageContract.View {
    override val presenter: ImageContract.Presenter by lazy {
        ImagePresenter(this, naverSearchRepository)
    }

    private lateinit var imageAdapter: ImageAdapter

    override var viewType: BaseSearchContract.ViewType =
        BaseSearchContract.ViewType.VIEW_SEARCH_BEFORE
        set(value) {
            if (field != value) {
                binding.viewType = field
                binding.invalidateAll()
            }
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            imageAdapter = ImageAdapter()
            binding.recyclerView.run {
                adapter = imageAdapter
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

            viewType = when {
                images.isEmpty() -> BaseSearchContract.ViewType.VIEW_SEARCH_NO_RESULT
                else -> BaseSearchContract.ViewType.VIEW_SEARCH_SUCCESS
            }

            images.isNotEmpty().then {
                imageAdapter.setData(images)
            }

            binding.invalidateAll()
        }
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Image>) {
        viewType = when {
            result.isEmpty() -> BaseSearchContract.ViewType.VIEW_SEARCH_NO_RESULT
            else -> BaseSearchContract.ViewType.VIEW_SEARCH_SUCCESS
        }

        if (result.isEmpty()) {
            imageAdapter.clear()
        } else {
            imageAdapter.setData(result)
        }

        binding.invalidateAll()
    }
}