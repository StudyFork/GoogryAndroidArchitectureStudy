package com.jay.architecturestudy.ui.image

import android.os.Bundle
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.databinding.FragmentImageBinding
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.util.then
import com.jay.architecturestudy.util.toPx
import com.jay.architecturestudy.widget.SpacesItemDecoration

class ImageFragment : BaseFragment<FragmentImageBinding, ImageViewModel>(R.layout.fragment_image) {
    override val viewModel: ImageViewModel by lazy {
        ImageViewModel(naverSearchRepository)
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
        binding.vm = viewModel
        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onCleared()
    }
}