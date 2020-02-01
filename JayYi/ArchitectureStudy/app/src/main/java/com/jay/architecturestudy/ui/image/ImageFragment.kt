package com.jay.architecturestudy.ui.image

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jay.architecturestudy.R
import com.jay.architecturestudy.databinding.FragmentImageBinding
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.ui.movie.MovieViewModel
import com.jay.architecturestudy.util.toPx
import com.jay.architecturestudy.widget.SpacesItemDecoration

class ImageFragment : BaseFragment<FragmentImageBinding, ImageViewModel>(R.layout.fragment_image) {
    override val viewModel: ImageViewModel by lazy {
        ViewModelProviders.of(this@ImageFragment, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ImageViewModel(
                    naverSearchRepository
                ) as T
            }

        })[ImageViewModel::class.java]
    }

    private lateinit var imageAdapter: ImageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            imageAdapter = ImageAdapter()
            binding.recyclerView.run {
                adapter = imageAdapter
                addItemDecoration(SpacesItemDecoration(12.toPx(), 6.toPx(), 11.toPx()))
            }
        }
        binding.vm = viewModel
        binding.lifecycleOwner = this
        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onCleared()
    }
}